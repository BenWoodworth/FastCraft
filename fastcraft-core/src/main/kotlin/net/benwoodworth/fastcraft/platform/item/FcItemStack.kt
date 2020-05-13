package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcItemStack {
    val type: FcMaterial
    val amount: Int
    val name: FcText
    val lore: List<FcText>

    val hasMetadata: Boolean

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int

    interface Factory {
        fun copyItem(
            itemStack: FcItemStack,
            amount: Int = itemStack.amount,
        ): FcItemStack

        fun parseOrNull(item: String, amount: Int = 1): FcItemStack?
    }
}
