package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.item.BukkitFcInventorySlot
import net.benwoodworth.fastcraft.bukkit.item.inventory
import net.benwoodworth.fastcraft.platform.item.FcInventorySlot
import net.benwoodworth.fastcraft.platform.player.FcPlayerInventory
import org.bukkit.inventory.PlayerInventory
import javax.inject.Inject

class BukkitFcPlayerInventory_1_7(
    override val inventory: PlayerInventory,
    private val slotFactory: BukkitFcInventorySlot.Factory,
) : BukkitFcPlayerInventory {
    override val storage: Collection<FcInventorySlot> = List(36) { slotIndex ->
        slotFactory.create(inventory, slotIndex)
    }

    override val helmet: FcInventorySlot
        get() = slotFactory.create(inventory, 103)

    override val chestPlate: FcInventorySlot
        get() = slotFactory.create(inventory, 102)

    override val leggings: FcInventorySlot
        get() = slotFactory.create(inventory, 101)

    override val boots: FcInventorySlot
        get() = slotFactory.create(inventory, 100)

    override fun equals(other: Any?): Boolean {
        return other is FcPlayerInventory && inventory == other.inventory
    }

    override fun hashCode(): Int {
        return inventory.hashCode()
    }

    class Factory @Inject constructor(
        private val slotFactory: BukkitFcInventorySlot.Factory,
    ) {
        fun create(inventory: PlayerInventory): BukkitFcPlayerInventory_1_7 {
            return BukkitFcPlayerInventory_1_7(
                inventory = inventory,
                slotFactory = slotFactory,
            )
        }
    }
}
