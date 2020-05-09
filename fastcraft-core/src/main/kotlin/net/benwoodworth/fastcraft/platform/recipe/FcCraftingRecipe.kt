package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItemStack
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.util.CancellableResult

interface FcCraftingRecipe {
    val id: String
    val ingredients: List<FcIngredient>
    val group: String?
    val exemplaryResult: FcItemStack

    fun prepare(
        player: FcPlayer,
        ingredients: Map<FcIngredient, FcItemStack>
    ): CancellableResult<FcCraftingRecipePrepared>

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}

