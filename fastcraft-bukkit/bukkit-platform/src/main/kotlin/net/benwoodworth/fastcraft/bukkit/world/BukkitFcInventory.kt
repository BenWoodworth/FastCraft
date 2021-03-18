package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.world.FcInventory
import org.bukkit.inventory.Inventory

interface FcInventory_Bukkit : FcInventory {
    val inventory: Inventory
}

val FcInventory.inventory: Inventory
    get() = (this as FcInventory_Bukkit).inventory
