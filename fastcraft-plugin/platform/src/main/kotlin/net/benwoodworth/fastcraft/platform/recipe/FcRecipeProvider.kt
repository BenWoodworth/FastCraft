package net.benwoodworth.fastcraft.platform.recipe

/**
 * Provides recipe from the server.
 */
interface FcRecipeProvider {

    /**
     * Get all the server's crafting recipes.
     *
     * @return a list of recipes.
     */
    fun getCraftingRecipes(): List<FcCraftingRecipe>
}
