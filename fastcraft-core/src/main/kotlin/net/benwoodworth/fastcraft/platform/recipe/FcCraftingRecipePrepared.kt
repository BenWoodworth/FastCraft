package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItemStack
import net.benwoodworth.fastcraft.util.CancellableResult

interface FcCraftingRecipePrepared {
    val recipe: FcCraftingRecipe
    val ingredients: Map<FcIngredient, FcItemStack>
    val resultsPreview: List<FcItemStack>

    /**
     * Only callable once.
     */
    fun craft(): CancellableResult<List<FcItemStack>>
}
