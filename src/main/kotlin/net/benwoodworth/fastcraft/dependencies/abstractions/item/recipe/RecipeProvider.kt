package net.benwoodworth.fastcraft.dependencies.abstractions.item.recipe

/**
 * Provides recipe from the server.
 */
interface RecipeProvider {

    /**
     * Get all the server's crafting recipes.
     *
     * @return a list of recipes
     */
    fun getCraftingRecipes(): List<CraftingRecipe>
}