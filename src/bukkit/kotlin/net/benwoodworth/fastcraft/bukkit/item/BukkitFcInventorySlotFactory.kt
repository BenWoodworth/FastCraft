package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcInventorySlot
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

interface BukkitFcInventorySlotFactory {
    fun createSlot(inventory: Inventory, slotIndex: Int): FcInventorySlot

    fun createSlot(getItem: () -> ItemStack?, setItem: (item: ItemStack) -> Unit): FcInventorySlot
}
