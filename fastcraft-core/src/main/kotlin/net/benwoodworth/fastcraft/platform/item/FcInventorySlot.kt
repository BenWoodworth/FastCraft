package net.benwoodworth.fastcraft.platform.item

interface FcInventorySlot {
    var item: FcItem?

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
