package net.benwoodworth.fastcraft.platform.bukkit.gui.inventory

import net.benwoodworth.fastcraft.platform.api.text.FcText
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder

interface BukkitInventoryFactory {

    fun create(height: Int, title: FcText? = null, owner: InventoryHolder? = null): Inventory

    fun create(type: InventoryType, title: FcText? = null, owner: InventoryHolder? = null): Inventory
}