package net.benwoodworth.fastcraft.platform.item

interface FcItemFactory {

    fun FcItem(
        type: FcItemType,
        amount: Int = 1,
        displayName: String? = null,
        lore: List<String>? = null
    ): FcItem

    fun FcItem.copy(
        type: FcItemType = this.type,
        amount: Int = this.amount,
        displayName: String? = this.displayName,
        lore: List<String>? = this.lore
    ): FcItem
}