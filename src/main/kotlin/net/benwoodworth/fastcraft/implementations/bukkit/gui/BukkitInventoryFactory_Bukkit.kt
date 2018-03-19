package net.benwoodworth.fastcraft.implementations.bukkit.gui

import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.text.bukkit.BukkitFcText_Bukkit
import org.bukkit.Server
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import javax.inject.Inject

class BukkitInventoryFactory_Bukkit @Inject constructor(
        private val server: Server,
        private val textFormatter: BukkitFcText_Bukkit.Formatter
) : BukkitInventoryFactory {

    override fun create(owner: InventoryHolder?, height: Int): Inventory {
        return server.createInventory(owner, height)
    }

    override fun create(owner: InventoryHolder?, height: Int, title: FcText): Inventory {
        val legacyTitle = textFormatter.format(title as BukkitFcText_Bukkit)
        return server.createInventory(owner, height, legacyTitle)
    }

    override fun create(owner: InventoryHolder?, type: InventoryType): Inventory {
        return server.createInventory(owner, type)
    }

    override fun create(owner: InventoryHolder?, type: InventoryType, title: FcText): Inventory {
        val legacyTitle = textFormatter.format(title as BukkitFcText_Bukkit)
        return server.createInventory(owner, type, legacyTitle)
    }
}