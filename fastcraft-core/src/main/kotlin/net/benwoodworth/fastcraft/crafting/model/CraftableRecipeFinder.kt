package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.FastCraftConfig
import net.benwoodworth.fastcraft.platform.item.FcItemStack
import net.benwoodworth.fastcraft.platform.item.FcMaterial
import net.benwoodworth.fastcraft.platform.item.FcMaterialComparator
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeProvider
import net.benwoodworth.fastcraft.platform.server.FcTask
import net.benwoodworth.fastcraft.util.CancellableResult
import net.benwoodworth.fastcraft.util.getPermutations
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class CraftableRecipeFinder(
    private val player: FcPlayer,
    private val recipeProvider: FcRecipeProvider,
    private val itemAmountsProvider: Provider<ItemAmounts>,
    materialComparator: FcMaterialComparator,
    private val taskFactory: FcTask.Factory,
    private val config: FastCraftConfig,
) {
    private val disabledPluginRecipes = setOf("recipemanager") //TODO Fix compatibility

    private var recipeLoadTask: FcTask? = null

    private val recipeComparator: Comparator<FcCraftingRecipe> =
        compareBy<FcCraftingRecipe, FcMaterial>(materialComparator) {
            it.exemplaryResult.type
        }.thenBy {
            it.exemplaryResult.amount
        }

    private val ingredientComparator = compareBy<Map.Entry<FcItemStack, Int>>(
        { (itemStack, _) -> itemStack.hasMetadata }, // Items with meta last
        { (_, amount) -> -amount }, // Greatest amount first
    )

    var listener: Listener? = null

    private companion object {
        val MAX_LOAD_TIME_PER_TICK = 1000 / 20 / 10
    }

    fun loadRecipes() {
        cancel()

        recipeLoadTask = taskFactory.startTask(delayTicks = 1) {
            val availableItems = itemAmountsProvider.get()
            player.inventory.storage.forEach { slot ->
                slot.itemStack?.let { itemStack -> availableItems += itemStack }
            }

            val recipeIterator = recipeProvider.getCraftingRecipes()
                .filter { !disabledPluginRecipes.contains(it.id.split(":").firstOrNull()) }
                .filter { !config.disabledRecipes.matches(it.id) }
                .sortedWith(recipeComparator)
                .flatMap { prepareCraftableRecipes(player, availableItems, it) }
                .iterator()

            recipeLoadTask = taskFactory.startTask(delayTicks = 1, intervalTicks = 1) { task ->
                val startTime = System.currentTimeMillis()
                val newRecipes = mutableListOf<FcCraftingRecipePrepared>()

                for (recipe in recipeIterator) {
                    if (recipe != null) {
                        newRecipes += recipe
                    }

                    val timeElapsed = System.currentTimeMillis() - startTime
                    if (timeElapsed > MAX_LOAD_TIME_PER_TICK) {
                        break
                    }
                }

                if (newRecipes.isNotEmpty()) {
                    listener?.onNewRecipesLoaded(newRecipes)
                }

                if (!recipeIterator.hasNext()) {
                    task.cancel()
                }
            }
        }
    }

    fun cancel() {
        recipeLoadTask?.cancel()
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

    @Singleton
    class Factory @Inject constructor(
        private val recipeProvider: FcRecipeProvider,
        private val itemAmountsProvider: Provider<ItemAmounts>,
        private val materialComparator: FcMaterialComparator,
        private val taskFactory: FcTask.Factory,
        private val config: FastCraftConfig,
    ) {
        fun create(player: FcPlayer): CraftableRecipeFinder {
            return CraftableRecipeFinder(
                player = player,
                recipeProvider = recipeProvider,
                itemAmountsProvider = itemAmountsProvider,
                materialComparator = materialComparator,
                taskFactory = taskFactory,
                config = config,
            )
        }
    }
}
