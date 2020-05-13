package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.platform.world.FcInventory
import net.benwoodworth.fastcraft.platform.world.FcInventorySlot

interface FcPlayerInventory : FcInventory {
    val storage: Collection<FcInventorySlot>
    val helmet: FcInventorySlot
    val chestPlate: FcInventorySlot
    val leggings: FcInventorySlot
    val boots: FcInventorySlot
}
