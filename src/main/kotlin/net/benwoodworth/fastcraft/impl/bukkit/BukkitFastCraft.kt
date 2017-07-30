package net.benwoodworth.fastcraft.impl.bukkit

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.dependencies.FastCraftImplementation
import org.bukkit.Bukkit
import org.bukkit.event.inventory.InventoryType
import org.bukkit.plugin.java.JavaPlugin

/**
 * Bukkit implementation of FastCraft.
 */
class BukkitFastCraft : FastCraftImplementation, JavaPlugin() {

    override lateinit var instance: FastCraft
        private set

    override fun onEnable() {

        val inv = Bukkit.createInventory(null, InventoryType.WORKBENCH)
        println(inv)
    }
}
