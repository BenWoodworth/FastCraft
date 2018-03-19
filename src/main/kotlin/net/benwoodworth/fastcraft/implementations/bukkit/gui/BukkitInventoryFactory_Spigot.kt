package net.benwoodworth.fastcraft.implementations.bukkit.gui

import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.text.spigot.BukkitFcText_Spigot
import org.bukkit.Server
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import javax.inject.Inject

class BukkitInventoryFactory_Spigot @Inject constructor(
        private val server: Server
) : BukkitInventoryFactory {

    override fun create(height: Int, title: FcText?, owner: InventoryHolder?): Inventory {
        return if (title == null) {
            server.createInventory(owner, height)
        } else {
            val legacyTitle = (title as BukkitFcText_Spigot).base.toLegacyText()
            server.createInventory(owner, height, legacyTitle)
        }
    }

    override fun create(type: InventoryType, title: FcText?, owner: InventoryHolder?): Inventory {
        return if (title == null) {
            server.createInventory(owner, type)
        } else {
            val legacyTitle = (title as BukkitFcText_Spigot).base.toLegacyText()
            server.createInventory(owner, type, legacyTitle)
        }
    }
}