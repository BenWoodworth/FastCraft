package net.benwoodworth.fastcraft.core.dependencies.recipes

/**
 * Provides recipe from the server.
 */
interface FcRecipeProvider {

    /**
     * Get recipe sources.
     *
     * @return the recipe sources
     */
    fun getRecipeSources(): List<FcRecipeSource>

    /**
     * Get the recipes provided by the native server.
     *
     * @return the server crafting recipes
     */
    fun getServerCraftingRecipes(): List<RecipeAdapter>
}
