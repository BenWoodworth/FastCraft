package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem

interface FcCraftingRecipePrepared {
    val recipe: FcCraftingRecipe

    val resultsPreview: List<FcItem>

    /**
     * Only callable once.
     */
    fun craft(): CraftResult

    class CraftResult(
        val results: List<FcItem>,
        val cancelled: Boolean
    )
}
