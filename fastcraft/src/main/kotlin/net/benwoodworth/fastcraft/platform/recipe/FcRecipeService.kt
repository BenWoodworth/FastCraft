package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.util.Extensible

/**
 * Provides recipe from the server.
 */
interface FcRecipeService : Extensible {

    /**
     * Returns a list of the server's crafting recipes.
     */
    fun getCraftingRecipes(): List<FcCraftingRecipe>
}
