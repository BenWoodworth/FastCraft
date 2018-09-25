package net.benwoodworth.fastcraft.platform.bukkit.gui.inventory

import net.benwoodworth.fastcraft.platform.api.text.FcText
import net.benwoodworth.fastcraft.platform.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.platform.bukkit.text.BukkitFcText_Bukkit
import org.bukkit.Server
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import javax.inject.Inject

@Suppress("ClassName")
class BukkitInventoryFactory_Bukkit @Inject constructor(
        private val server: Server,
        private val textConverter: BukkitFcText_Bukkit.Converter
) : BukkitInventoryFactory {

    override fun create(height: Int, title: FcText?, owner: InventoryHolder?): Inventory {
        return if (title == null) {
            server.createInventory(owner, height)
        } else {
            val legacyTitle = textConverter.toLegacyText(title as BukkitFcText)
            return server.createInventory(owner, height, legacyTitle)
        }
    }

    override fun create(type: InventoryType, title: FcText?, owner: InventoryHolder?): Inventory {
        return if (title == null) {
            server.createInventory(owner, type)
        } else {
            val legacyTitle = textConverter.toLegacyText(title as BukkitFcText)
            return server.createInventory(owner, type, legacyTitle)
        }
    }
}