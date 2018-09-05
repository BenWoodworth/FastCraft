package net.benwoodworth.fastcraft.platform.impl.bukkit.gui.inventory

import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

interface BukkitInventoryFactory {

    fun create(height: Int, title: FcText? = null, owner: InventoryHolder? = null): Inventory

    fun create(type: InventoryType, title: FcText? = null, owner: InventoryHolder? = null): Inventory
}