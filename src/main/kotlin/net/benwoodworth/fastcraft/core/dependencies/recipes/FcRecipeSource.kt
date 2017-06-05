package net.benwoodworth.fastcraft.core.dependencies.recipes

import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem

/**
 * A source of recipes. (e.g. from plugins that register custom recipes)
 *
 * @param TItem the item type
 */
interface FcRecipeSource<TItem : FcItem<*>> {

    /**
     * Get this recipe source's crafting recipes.
     *
     * @return the crafting recipes
     */
    fun getCraftingRecipes(): List<FcModifiedRecipe<TItem>>
}
