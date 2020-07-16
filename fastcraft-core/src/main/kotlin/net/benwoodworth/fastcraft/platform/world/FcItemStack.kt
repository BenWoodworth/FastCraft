package net.benwoodworth.fastcraft.platform.world

import net.benwoodworth.fastcraft.platform.text.FcText

inline class FcItemStack(val value: Any) {
    interface TypeClass {
        val FcItemStack.type: FcItem
        val FcItemStack.amount: Int
        val FcItemStack.name: FcText
        val FcItemStack.lore: List<FcText>

        val FcItemStack.hasMetadata: Boolean
    }

    interface Factory {
        fun create(
            item: FcItem,
            amount: Int = 1,
        ): FcItemStack

        fun copyItem(
            itemStack: FcItemStack,
            amount: Int,
        ): FcItemStack

        fun parseOrNull(itemStr: String, amount: Int = 1): FcItemStack?
    }
}
