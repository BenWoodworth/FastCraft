package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.util.Extensible

interface FcRecipeService : Extensible {

    fun getCraftingRecipes(): List<FcCraftingRecipe>
}
