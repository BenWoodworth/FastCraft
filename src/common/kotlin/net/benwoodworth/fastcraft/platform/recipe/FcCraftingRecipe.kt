package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem

interface FcCraftingRecipe {
    val id: String

    val ingredients: List<FcIngredient>

    fun prepare(ingredients: List<FcItem>): FcCraftingRecipePrepared
}

