package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeProvider
import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemOrderComparator
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import net.benwoodworth.fastcraft.util.CancellableResult
import net.benwoodworth.fastcraft.util.getPermutations
import java.util.*
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.collections.HashMap

@Singleton
class CraftableRecipeFinder @Inject constructor(
    private val recipeProvider: FcRecipeProvider,
    private val itemAmountsProvider: Provider<ItemAmounts>,
    private val tcPlayer: FcPlayer.TypeClass,
    materialComparator: FcItemOrderComparator,
    private val taskFactory: FcTask.Factory,
    private val config: FastCraftConfig,
    private val tcItemStack: FcItemStack.TypeClass,
) {
    private val NANOS_PER_TICK = 1000000000L / 20L

    private val disabledPluginRecipes = setOf("recipemanager") //TODO Fix compatibility

    private var recipeLoadTasks: HashMap<UUID, FcTask> = HashMap()

    private val recipeComparator: Comparator<FcCraftingRecipe> =
        compareBy<FcCraftingRecipe, FcItem>(materialComparator) {
            tcItemStack.run { it.exemplaryResult.type }
        }.thenBy {
            tcItemStack.run { it.exemplaryResult.amount }
        }

    private val ingredientComparator = compareBy<Map.Entry<FcItemStack, Int>>(
        { (itemStack, _) -> tcItemStack.run { itemStack.hasMetadata } }, // Items with meta last
        { (_, amount) -> -amount }, // Greatest amount first
    )

    fun loadRecipes(player: FcPlayer, listener: Listener) {
        val uuid = tcPlayer.run { player.uuid }
        recipeLoadTasks[uuid] = taskFactory.startTask(delayTicks = 1) {
            val availableItems = itemAmountsProvider.get()
            tcPlayer.run { player.inventory }.storage.forEach { slot ->
                slot.itemStack?.let { itemStack -> availableItems += itemStack }
            }

            val recipeIterator = recipeProvider.getCraftingRecipes()
                .filter { !disabledPluginRecipes.contains(it.id.split(":").firstOrNull()) }
                .filter { !config.disableRecipes.recipeIdsRegex.matches(it.id) }
                .sortedWith(recipeComparator)
                .flatMap { prepareCraftableRecipes(player, availableItems, it) }
                .iterator()

            recipeLoadTasks[uuid] =
                taskFactory.startTask(delayTicks = 1, intervalTicks = 1) { task ->
                    val startTime = System.nanoTime()
                    val newRecipes = mutableListOf<FcCraftingRecipePrepared>()

                    for (recipe in recipeIterator) {
                        if (recipe != null) {
                            newRecipes += recipe
                        }

                        val maxCalcTime =
                            NANOS_PER_TICK * config.recipeCalculations.maxTickUsage / recipeLoadTasks.count()
                        val timeElapsed = System.nanoTime() - startTime
                        if (timeElapsed >= maxCalcTime) {
                            break
                        }
                    }

                    if (newRecipes.isNotEmpty()) {
                        listener.onNewRecipesLoaded(newRecipes)
                    }

                    if (!recipeIterator.hasNext()) {
                        task.cancel()
                    }
                }
        }
    }

    fun cancel(player: FcPlayer) {
        recipeLoadTasks.remove(tcPlayer.run { player.uuid })?.cancel()
    }

    private fun prepareCraftableRecipes(
        player: FcPlayer,
        availableItems: ItemAmounts,
        recipe: FcCraftingRecipe,
    ): Sequence<FcCraftingRecipePrepared?> = sequence {
        val results = mutableSetOf<List<FcItemStack>>()

        val ingredients = recipe.ingredients

        val possibleIngredientItems = ingredients.map { ingredient ->
            availableItems
                .asMap().entries
                .filter { (itemStack, _) -> ingredient.matches(itemStack) }
                .sortedWith(ingredientComparator)
                .map { (itemStack, _) -> itemStack }
        }

        val itemsUsed = itemAmountsProvider.get()
        possibleIngredientItems.getPermutations().forEach { permutation ->
            itemsUsed.clear()
            permutation.forEach { itemsUsed += it }

            val enoughItems = itemsUsed.asMap().all { (itemStack, amount) ->
                availableItems[itemStack] >= amount
            }

            if (enoughItems) {
                val ingredientItems = ingredients
                    .mapIndexed { i, ingredient -> ingredient to permutation[i] }
                    .toMap()

                val prepared = recipe.prepare(player, ingredientItems)

                if (prepared is CancellableResult.Result) {
                    val resultPreview = prepared.result.resultsPreview
                    if (resultPreview !in results) {
                        results += resultPreview
                        yield(prepared.result)
                    }
                }
            }

            yield(null)
        }
    }

    interface Listener {
        fun onNewRecipesLoaded(newRecipes: List<FcCraftingRecipePrepared>) {}
    }
}
