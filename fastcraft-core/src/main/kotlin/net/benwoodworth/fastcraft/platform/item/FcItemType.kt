package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcItemType {
    val itemName: FcText
    val blockName: FcText
    val maxAmount: Int

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
