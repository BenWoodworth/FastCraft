package net.benwoodworth.fastcraft.platform.recipe

interface FcRecipeService {
    fun getCraftingRecipes(): Sequence<FcCraftingRecipe>
}
