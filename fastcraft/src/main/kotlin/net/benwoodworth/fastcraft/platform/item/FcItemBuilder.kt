package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.Extensible

interface FcItemBuilder : Extensible {

    fun copyFrom(value: FcItem): FcItemBuilder.WithType

    fun type(value: FcItemType): FcItemBuilder.WithType

    interface WithType {

        fun type(value: FcItemType): FcItemBuilder.WithType

        fun amount(value: Int): FcItemBuilder.WithType

        fun displayName(value: FcText?): FcItemBuilder.WithType

        fun lore(value: List<FcText>): FcItemBuilder.WithType

        fun build(): FcItem
    }
}
