package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.bukkit.dependencies.inventory.BukkitItem
import net.benwoodworth.fastcraft.core.FastCraft
import org.bukkit.plugin.java.JavaPlugin

/**
 * The Bukkit implementation of FastCraft.
 */
class BukkitFastCraft : JavaPlugin() {

    private var instance: FastCraft<BukkitItem>? = null

    override fun onEnable() {
        instance = DaggerBukkitFastCraftComponent
            .builder()
            .bukkitFastCraftModule(BukkitFastCraftModule(this))
            .build()
            .getFastCraft()
    }

    fun getInstance(): FastCraft<BukkitItem> {
        return instance ?: throw Exception("FastCraft instance not yet initialized")
    }
}
