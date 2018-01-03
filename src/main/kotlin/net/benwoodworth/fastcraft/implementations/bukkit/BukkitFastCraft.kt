package net.benwoodworth.fastcraft.implementations.bukkit

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import net.benwoodworth.fastcraft.implementations.bukkit.config.BukkitModuleConfig
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitModuleEvent
import net.benwoodworth.fastcraft.implementations.bukkit.gui.BukkitModuleGui
import net.benwoodworth.fastcraft.implementations.bukkit.item.BukkitModuleItem
import net.benwoodworth.fastcraft.implementations.bukkit.player.BukkitModulePlayer
import net.benwoodworth.fastcraft.implementations.bukkit.server.BukkitModuleServer
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitModuleText
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bstats.bukkit.Metrics as BukkitBstatsMetrics

/**
 * Bukkit implementation of FastCraft.
 */
object BukkitFastCraft : FastCraftImplementation, JavaPlugin() {

    override lateinit var instance: FastCraft
        private set

    @Suppress("DEPRECATION") // TODO Don't suppress deprecation
    override fun onEnable() {
        val fastCraftBuilder = DaggerBukkitDependenciesComponent.builder()
                .bukkitModuleConfig(BukkitModuleConfig())
                .bukkitModuleEvent(BukkitModuleEvent(this))
                .bukkitModuleGui(BukkitModuleGui(this))
                .bukkitModuleItem(BukkitModuleItem())
                .bukkitModulePlayer(BukkitModulePlayer())
                .bukkitModuleServer(BukkitModuleServer(this))
                .bukkitModuleText(BukkitModuleText())

        instance = fastCraftBuilder.build().getFastCraft()

        BukkitBstatsMetrics(this)

        println(Bukkit.getServer().javaClass.`package`.name)
    }
}
