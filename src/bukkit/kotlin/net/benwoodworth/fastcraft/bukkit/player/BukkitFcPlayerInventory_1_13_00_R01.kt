package net.benwoodworth.fastcraft.bukkit.player

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcInventorySlotFactory
import net.benwoodworth.fastcraft.platform.item.FcInventorySlot
import org.bukkit.inventory.PlayerInventory

@AutoFactory
class BukkitFcPlayerInventory_1_13_00_R01(
    override val inventory: PlayerInventory,
    @Provided private val slotFactory: BukkitFcInventorySlotFactory
) : BukkitFcPlayerInventory {
    override val storage: Sequence<FcInventorySlot> = sequence {
        for (slotIndex in 0..35) {
            yield(slotFactory.createSlot(inventory, slotIndex))
        }
    }

    override val helmet: FcInventorySlot
        get() = slotFactory.createSlot(inventory::getHelmet, inventory::setHelmet)

    override val chestPlate: FcInventorySlot
        get() = slotFactory.createSlot(inventory::getChestplate, inventory::setChestplate)

    override val leggings: FcInventorySlot
        get() = slotFactory.createSlot(inventory::getLeggings, inventory::setLeggings)

    override val boots: FcInventorySlot
        get() = slotFactory.createSlot(inventory::getBoots, inventory::setBoots)
}
