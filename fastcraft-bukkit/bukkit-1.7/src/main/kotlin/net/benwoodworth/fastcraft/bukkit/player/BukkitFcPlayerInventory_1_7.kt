package net.benwoodworth.fastcraft.bukkit.player

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.inventory
import net.benwoodworth.fastcraft.platform.item.FcInventorySlot
import net.benwoodworth.fastcraft.platform.player.FcPlayerInventory
import org.bukkit.inventory.PlayerInventory

@AutoFactory
class BukkitFcPlayerInventory_1_7(
    override val inventory: PlayerInventory,
    @Provided private val slotFactory: net.benwoodworth.fastcraft.bukkit.item.BukkitFcInventorySlot.Factory,
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
}
