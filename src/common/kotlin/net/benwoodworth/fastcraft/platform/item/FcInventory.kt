package net.benwoodworth.fastcraft.platform.item

interface FcInventory {
    val slots: Iterable<FcInventorySlot>

    operator fun get(slot: FcInventorySlot): FcItem?

    operator fun set(slot: FcInventorySlot, item: FcItem?)
}
