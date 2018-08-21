package net.benwoodworth.fastcraft.platform.impl.bukkit

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import org.bukkit.plugin.java.JavaPlugin
import org.bstats.bukkit.Metrics as BukkitBStatsMetrics

/**
 * Bukkit implementation of FastCraft.
 */
class BukkitFastCraft : FastCraftImplementation, JavaPlugin() {

    override lateinit var instance: FastCraft
        private set

    override fun onEnable() {
        val fastCraftBuilder = DaggerBukkitFastCraftComponent.builder()
                .bukkitFastCraftModule(BukkitFastCraftModule(this))

        instance = fastCraftBuilder.build().getFastCraft()

        BukkitBStatsMetrics(this)
    }
}
