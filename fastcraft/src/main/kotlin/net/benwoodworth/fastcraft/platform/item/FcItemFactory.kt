package net.benwoodworth.fastcraft.platform.item

import net.benwoodworth.fastcraft.platform.text.FcLegacyText

interface FcItemFactory {

    fun FcItem(
        type: FcItemType,
        amount: Int = 1,
        displayName: String? = null,
        lore: List<String>? = null
    ): FcItem

    fun FcItem(
        copy: FcItem,
        type: FcItemType = copy.type,
        amount: Int = copy.amount,
        displayName: FcLegacyText? = copy.displayName,
        lore: List<FcLegacyText>? = copy.lore
    ): FcItem
}