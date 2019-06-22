package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem

interface FcCraftingRecipePrepared {

    val ingredients: List<FcItem>

    fun getResultPreview(): List<FcItem>

    fun craft(): List<FcItem>
}