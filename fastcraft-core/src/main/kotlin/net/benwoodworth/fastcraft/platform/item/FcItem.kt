package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcItem {
    val type: FcItemType
    val amount: Int
    val name: FcText
    val lore: List<FcText>

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    interface Factory {
        fun copyItem(
            item: FcItem,
            amount: Int = item.amount,
        ): FcItem
    }
}
