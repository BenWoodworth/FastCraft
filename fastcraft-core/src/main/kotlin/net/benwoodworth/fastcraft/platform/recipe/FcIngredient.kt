package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItemStack

interface FcIngredient {
    fun matches(itemStack: FcItemStack): Boolean

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
