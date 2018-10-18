package net.benwoodworth.fastcraft.platform.recipe

/**
 * Provides recipe from the server.
 */
interface FcRecipeService {

    /**
     * Returns a list of the server's crafting recipes.
     */
    fun getCraftingRecipes(): List<FcCraftingRecipe>
}
