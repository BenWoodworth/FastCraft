package net.benwoodworth.fastcraft.platform.world

interface FcInventorySlot {
    var itemStack: FcItemStack?

    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
