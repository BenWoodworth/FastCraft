package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.util.Extensible

interface FcCraftingRecipe : Extensible {

    val id: String

    val ingredients: List<FcIngredient>
}

