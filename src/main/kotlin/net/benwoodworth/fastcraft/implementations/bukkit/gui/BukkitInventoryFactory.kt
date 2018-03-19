package net.benwoodworth.fastcraft.implementations.bukkit.gui

import net.benwoodworth.fastcraft.dependencies.text.FcText
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

interface BukkitInventoryFactory {

    fun create(owner: InventoryHolder? = null, height: Int): Inventory

    fun create(owner: InventoryHolder? = null, height: Int, title: FcText): Inventory

    fun create(owner: InventoryHolder? = null, type: InventoryType): Inventory

    fun create(owner: InventoryHolder? = null, type: InventoryType, title: FcText): Inventory
}