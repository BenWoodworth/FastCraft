package net.benwoodworth.fastcraft.platform.item

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
        displayName: String? = copy.displayName,
        lore: List<String>? = copy.lore
    ): FcItem
}