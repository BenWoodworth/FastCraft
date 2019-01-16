package net.benwoodworth.fastcraft.platform.recipe

interface FcCraftingRecipe {

    val id: String

    val ingredients: List<FcIngredient>
}

