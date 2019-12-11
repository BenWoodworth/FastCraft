package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem

interface FcCraftingRecipePrepared {
    val recipe: FcCraftingRecipe

    val ingredients: List<FcItem>

    fun getResultPreview(craftMaxAmount: Boolean): List<FcItem>

    fun craft(craftMaxAmount: Boolean): List<FcItem>

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
