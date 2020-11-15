package net.benwoodworth.fastcraft.platform.recipe

interface FcRecipeProvider {
    fun getCraftingRecipes(): Sequence<FcCraftingRecipe>
}
