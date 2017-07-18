package net.benwoodworth.fastcraft.dependencies.item.recipe

/**
 * Provides recipe from the server.
 */
interface RecipeProvider {

    /**
     * Get all the recipes on the server.
     *
     * @return a list of the server's recipes
     */
    fun getServerRecipes(): List<Recipe>
}
