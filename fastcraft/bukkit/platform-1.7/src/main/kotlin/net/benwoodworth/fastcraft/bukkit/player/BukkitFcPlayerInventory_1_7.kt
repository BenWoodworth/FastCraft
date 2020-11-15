package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.world.BukkitFcInventorySlot
import net.benwoodworth.fastcraft.bukkit.world.inventory
import net.benwoodworth.fastcraft.platform.player.FcPlayerInventory
import net.benwoodworth.fastcraft.platform.world.FcInventorySlot
import org.bukkit.inventory.PlayerInventory
import javax.inject.Inject

class BukkitFcPlayerInventory_1_7(
    override val inventory: PlayerInventory,
    private val fcInventorySlotFactory: BukkitFcInventorySlot.Factory,
) : BukkitFcPlayerInventory {
    override val storage: Collection<FcInventorySlot> = List(36) { slotIndex ->
        fcInventorySlotFactory.create(inventory, slotIndex)
    }

    override val helmet: FcInventorySlot
        get() = fcInventorySlotFactory.create(inventory, 103)

    override val chestPlate: FcInventorySlot
        get() = fcInventorySlotFactory.create(inventory, 102)

    override val leggings: FcInventorySlot
        get() = fcInventorySlotFactory.create(inventory, 101)

    override val boots: FcInventorySlot
        get() = fcInventorySlotFactory.create(inventory, 100)

    override fun equals(other: Any?): Boolean {
        return other is FcPlayerInventory && inventory == other.inventory
    }

    override fun hashCode(): Int {
        return inventory.hashCode()
    }

    class Factory @Inject constructor(
        private val fcInventorySlotFactory: BukkitFcInventorySlot.Factory,
    ) {
        fun create(inventory: PlayerInventory): BukkitFcPlayerInventory_1_7 {
            return BukkitFcPlayerInventory_1_7(
                inventory = inventory,
                fcInventorySlotFactory = fcInventorySlotFactory,
            )
        }
    }
}
