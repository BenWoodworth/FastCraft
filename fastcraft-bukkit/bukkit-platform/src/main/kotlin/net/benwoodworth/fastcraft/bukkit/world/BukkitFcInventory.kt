package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcInventory
import org.bukkit.inventory.Inventory

interface BukkitFcInventory : FcInventory {
    val inventory: Inventory
}

val FcInventory.inventory: Inventory
    get() = (this as BukkitFcInventory).inventory
