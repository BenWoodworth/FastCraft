package net.benwoodworth.fastcraft.implementations.bukkit.gui

import net.benwoodworth.fastcraft.dependencies.text.FcText
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

interface BukkitInventoryFactory {

    fun create(height: Int, title: FcText? = null, owner: InventoryHolder? = null): Inventory

    fun create(type: InventoryType, title: FcText? = null, owner: InventoryHolder? = null): Inventory
}