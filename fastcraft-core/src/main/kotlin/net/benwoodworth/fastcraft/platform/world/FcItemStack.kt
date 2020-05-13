package net.benwoodworth.fastcraft.platform.world

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcItemStack {
    val type: FcItem
    val amount: Int
    val name: FcText
    val lore: List<FcText>

    val hasMetadata: Boolean

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    interface Factory {
        fun create(
            item: FcItem,
            amount: Int = 1,
        ): FcItemStack

        fun copyItem(
            itemStack: FcItemStack,
            amount: Int = itemStack.amount,
        ): FcItemStack

        fun parseOrNull(itemStr: String, amount: Int = 1): FcItemStack?
    }
}
