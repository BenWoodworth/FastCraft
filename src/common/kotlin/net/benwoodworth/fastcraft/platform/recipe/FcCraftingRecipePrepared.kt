package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.util.CancellableResult

interface FcCraftingRecipePrepared {
    val recipe: FcCraftingRecipe

    val ingredients: List<FcItem>

    /**
     * Only callable once.
     */
    fun craftPreview(): CancellableResult<List<FcItem>>

    /**
     * Only callable once.
     */
    fun craft(): CancellableResult<List<FcItem>>

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
