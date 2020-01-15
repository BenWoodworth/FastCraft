package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.util.CancellableResult

interface FcCraftingRecipe {
    val id: String
    val ingredients: List<FcIngredient>

    fun prepare(player: FcPlayer, ingredients: Map<FcIngredient, FcItem>): CancellableResult<FcCraftingRecipePrepared>

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}

