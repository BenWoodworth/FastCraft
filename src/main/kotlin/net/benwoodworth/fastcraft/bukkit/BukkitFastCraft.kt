package net.benwoodworth.fastcraft.bukkit

import net.benwoodworth.fastcraft.bukkit.dependencies.inventory.BukkitItem
import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import org.bukkit.plugin.java.JavaPlugin

/**
 * The Bukkit implementation of FastCraft.
 */
class BukkitFastCraft : JavaPlugin(), FastCraftImplementation<FastCraft<BukkitItem>> {

    lateinit override var instance: FastCraft<BukkitItem>
        private set

    override fun onEnable() {
//        instance = DaggerBukkitFastCraftComponent.builder()
//            .bukkitFastCraftModule(BukkitFastCraftModule(this))
//            .build()
//            .getFastCraft()
    }
}
