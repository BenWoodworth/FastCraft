package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcText

interface FcItemFactory {

    fun createFcItem(
        type: FcItemType,
        amount: Int = 1,
        displayName: FcText? = null,
        lore: List<FcText>? = null
    ): FcItem

    fun createFcItem(
        copy: FcItem,
        type: FcItemType = copy.type,
        amount: Int = copy.amount,
        displayName: FcText? = copy.displayName,
        lore: List<FcText>? = copy.lore
    ): FcItem
}