package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import net.benwoodworth.fastcraft.util.Extensible

interface FcItemBuilder : Extensible {

    fun type(type: FcItemType): FcItemBuilder

    fun type(type: FcItemTypes.() -> FcItemType): FcItemBuilder

    fun amount(amount: Int): FcItemBuilder

    fun displayName(displayName: FcText): FcItemBuilder

    fun displayName(displayName: (FcTextBuilder) -> FcText): FcItemBuilder

    fun lore(lore: List<FcText>): FcItemBuilder

    fun durability(durability: Int): FcItemBuilder

    fun build(): FcItem
}
