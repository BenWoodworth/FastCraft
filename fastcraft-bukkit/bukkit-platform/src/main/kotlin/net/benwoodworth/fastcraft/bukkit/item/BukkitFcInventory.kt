package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcInventory
import org.bukkit.inventory.Inventory

interface BukkitFcInventory : FcInventory {
    val inventory: Inventory

    companion object {
        val FcInventory.inventory: Inventory
            get() = (this as BukkitFcInventory).inventory
    }
}
