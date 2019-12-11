package net.benwoodworth.fastcraft.bukkit.player

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcInventorySlotFactory
import net.benwoodworth.fastcraft.bukkit.item.inventory
import net.benwoodworth.fastcraft.platform.item.FcInventorySlot
import net.benwoodworth.fastcraft.platform.player.FcPlayerInventory
import org.bukkit.inventory.PlayerInventory

@AutoFactory
class BukkitFcPlayerInventory_1_13_00_R01(
    override val inventory: PlayerInventory,
    @Provided private val slotFactory: BukkitFcInventorySlotFactory
) : BukkitFcPlayerInventory {
    override val storage: Collection<FcInventorySlot> = List(36) { slotIndex ->
        slotFactory.createSlot(inventory, slotIndex)
    }

    override val helmet: FcInventorySlot
        get() = slotFactory.createSlot(inventory::getHelmet, inventory::setHelmet)

    override val chestPlate: FcInventorySlot
        get() = slotFactory.createSlot(inventory::getChestplate, inventory::setChestplate)

    override val leggings: FcInventorySlot
        get() = slotFactory.createSlot(inventory::getLeggings, inventory::setLeggings)

    override val boots: FcInventorySlot
        get() = slotFactory.createSlot(inventory::getBoots, inventory::setBoots)

    override fun equals(other: Any?): Boolean {
        return other is FcPlayerInventory && inventory == other.inventory
    }

    override fun hashCode(): Int {
        return inventory.hashCode()
    }
}
