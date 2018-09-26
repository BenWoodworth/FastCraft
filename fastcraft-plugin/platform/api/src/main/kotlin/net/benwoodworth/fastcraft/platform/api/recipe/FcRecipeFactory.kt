package net.benwoodworth.fastcraft.platform.api.recipe

/**
 * Provides recipe from the server.
 */
interface FcRecipeFactory {

    /**
     * Get all the server's crafting recipes.
     *
     * @return a list of recipes.
     */
    fun getCraftingRecipes(): List<FcCraftingRecipe>
}
