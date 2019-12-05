package net.benwoodworth.fastcraft.platform.item

interface FcItemFactory {
    fun FcItem.copy(
        amount: Int = this.amount
    ): FcItem
}
