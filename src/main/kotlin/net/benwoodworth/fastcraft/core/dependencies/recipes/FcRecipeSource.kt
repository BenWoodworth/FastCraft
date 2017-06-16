package net.benwoodworth.fastcraft.core.dependencies.recipes

/**
 * A source of recipes. (e.g. from plugins that register custom recipes)
 */
interface FcRecipeSource {

    /**
     * Get this recipe source's crafting recipes.
     *
     * @return the crafting recipes
     */
    fun getCraftingRecipes(): List<ModifiedRecipeAdapter>
}
