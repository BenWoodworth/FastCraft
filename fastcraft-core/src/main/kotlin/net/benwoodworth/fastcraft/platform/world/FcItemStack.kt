package net.benwoodworth.fastcraft.platform.world

import net.benwoodworth.fastcraft.platform.text.FcText

@JvmInline
value class FcItemStack(val value: Any) {
    interface Operations {
        var FcItemStack.type: FcItem
        var FcItemStack.amount: Int
        val FcItemStack.name: FcText
        val FcItemStack.lore: List<FcText>

        val FcItemStack.hasMetadata: Boolean

        fun FcItemStack.copy(): FcItemStack
    }

    interface Factory {
        fun create(
            item: FcItem,
            amount: Int = 1,
        ): FcItemStack

        fun parseOrNull(itemStr: String, amount: Int = 1): FcItemStack?
    }
}
