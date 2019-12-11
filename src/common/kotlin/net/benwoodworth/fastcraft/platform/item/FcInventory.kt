package net.benwoodworth.fastcraft.platform.item

interface FcInventory {
    override fun equals(other: Any?): Boolean

    override fun hashCode(): Int
}
