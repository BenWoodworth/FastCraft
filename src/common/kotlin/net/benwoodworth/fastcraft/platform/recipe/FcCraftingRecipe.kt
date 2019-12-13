package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.player.FcPlayer

interface FcCraftingRecipe {
    val id: String

    val ingredients: List<FcIngredient>

    fun prepare(player: FcPlayer, ingredients: Map<FcIngredient, FcItem>): FcCraftingRecipePrepared

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}

