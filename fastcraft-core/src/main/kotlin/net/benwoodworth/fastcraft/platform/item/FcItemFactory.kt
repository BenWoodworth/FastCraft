package net.benwoodworth.fastcraft.platform.item

interface FcItemFactory {
    fun copyItem(
        item: FcItem,
        amount: Int = item.amount
    ): FcItem
}
