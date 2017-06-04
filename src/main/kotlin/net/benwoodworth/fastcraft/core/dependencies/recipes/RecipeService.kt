package net.benwoodworth.fastcraft.core.dependencies.recipes

import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem

/**
 * Provides recipe from the server.
 *
 * @param TItem the item type
 */
interface RecipeService<TItem : FcItem<*>> {

    /**
     * Get recipe sources.
     *
     * @return the recipe sources
     */
    fun getRecipeSources(): List<RecipeSource<TItem>>

    /**
     * Get the recipes provided by the native server.
     *
     * @return the server crafting recipes
     */
    fun getServerCraftingRecipes(): List<FcRecipe<TItem>>
}
