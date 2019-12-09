package net.benwoodworth.fastcraft.platform.item

interface FcInventory : Iterable<FcInventorySlot> {
    val slots: Iterable<FcInventorySlot>

    override fun iterator(): Iterator<FcInventorySlot> {
        return slots.iterator()
    }
}
