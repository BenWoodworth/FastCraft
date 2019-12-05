package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.text.FcText

interface FcIngredient {
    val name: FcText

    fun matches(item: FcItem): Boolean

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
