package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.util.CancellableResult

interface FcCraftingRecipePrepared {
    val recipe: FcCraftingRecipe
    val ingredients: Map<FcIngredient, FcItem>
    val resultsPreview: List<FcItem>

    /**
     * Only callable once.
     */
    fun craft(): CancellableResult<List<FcItem>>
}
