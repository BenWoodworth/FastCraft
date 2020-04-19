package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypeComparator
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
    itemTypeComparator: FcItemTypeComparator,
    private val taskFactory: FcTask.Factory,
) {
    private var recipeLoadTask: FcTask? = null

    private val recipeComparator: Comparator<FcCraftingRecipe> =
        compareBy<FcCraftingRecipe, FcItemType>(itemTypeComparator) {
            it.exemplaryResult.type
        }.thenBy {
            it.exemplaryResult.amount
        }

    private val ingredientComparator = compareBy<Map.Entry<FcItem, Int>>(
        { (item, _) -> item.hasMetadata }, // Items with meta last
        { (_, amount) -> -amount }, // Greatest amount first
    )

    var listener: Listener? = null

    private companion object {
        val MAX_LOAD_TIME_PER_TICK = 1000 / 20 / 10
    }

    fun loadRecipes() {
        cancel()

        val availableItems = itemAmountsProvider.get()
        player.inventory.storage.forEach { slot ->
            slot.item?.let { item -> availableItems += item }
        }

        val recipeIterator = recipeProvider.getCraftingRecipes()
            .sortedWith(recipeComparator)
            .flatMap { prepareCraftableRecipes(player, availableItems, it) }
            .iterator()

        recipeLoadTask = taskFactory.startTask(delaySeconds = 1.0 / 20.0, intervalSeconds = 1.0 / 20.0) { task ->
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

    fun cancel() {
        recipeLoadTask?.cancel()
    }

    private fun prepareCraftableRecipes(
        player: FcPlayer,
        availableItems: ItemAmounts,
        recipe: FcCraftingRecipe,
    ): Sequence<FcCraftingRecipePrepared?> = sequence {
        val results = mutableSetOf<List<FcItem>>()

        val ingredients = recipe.ingredients

        val possibleIngredientItems = ingredients.map { ingredient ->
            availableItems
                .asMap().entries
                .filter { (item, _) -> ingredient.matches(item) }
                .sortedWith(ingredientComparator)
                .map { (item, _) -> item }
        }

        val itemsUsed = itemAmountsProvider.get()
        possibleIngredientItems.getPermutations().forEach { permutation ->
            itemsUsed.clear()
            permutation.forEach { itemsUsed += it }

            val enoughItems = itemsUsed.asMap().all { (item, amount) ->
                availableItems[item] >= amount
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
        private val itemTypeComparator: FcItemTypeComparator,
        private val taskFactory: FcTask.Factory,
    ) {
        fun create(player: FcPlayer): CraftableRecipeFinder {
            return CraftableRecipeFinder(
                player = player,
                recipeProvider = recipeProvider,
                itemAmountsProvider = itemAmountsProvider,
                itemTypeComparator = itemTypeComparator,
                taskFactory = taskFactory,
            )
        }
    }
}
