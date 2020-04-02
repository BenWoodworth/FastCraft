package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeService
import net.benwoodworth.fastcraft.util.CancellableResult
import net.benwoodworth.fastcraft.util.getPermutations
import javax.inject.Inject
import javax.inject.Provider

class CraftableRecipeFinder @Inject constructor(
    private val recipeService: FcRecipeService,
    private val itemAmountsProvider: Provider<ItemAmounts>,
) {
    fun getCraftableRecipes(
        player: FcPlayer,
        availableItems: ItemAmounts,
    ): Sequence<FcCraftingRecipePrepared> {
        return recipeService.getCraftingRecipes()
            .flatMap { prepareCraftableRecipes(player, availableItems, it) }
    }

    private fun prepareCraftableRecipes(
        player: FcPlayer,
        availableItems: ItemAmounts,
        recipe: FcCraftingRecipe,
    ): Sequence<FcCraftingRecipePrepared> = sequence {
        val ingredients = recipe.ingredients

        val possibleIngredientItems = ingredients.map { ingredient ->
            availableItems.asMap().keys.filter { item ->
                ingredient.matches(item)
            }
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
                    yield(prepared.result)
                }
            }
        }
    }
}
