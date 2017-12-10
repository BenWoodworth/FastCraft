package net.benwoodworth.fastcraft.dependencies.bukkit

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import net.benwoodworth.fastcraft.dependencies.bukkit.config.BukkitModuleConfig
import net.benwoodworth.fastcraft.dependencies.bukkit.event.BukkitModuleEvent
import net.benwoodworth.fastcraft.dependencies.bukkit.gui.BukkitModuleGui
import net.benwoodworth.fastcraft.dependencies.bukkit.item.BukkitModuleItem
import net.benwoodworth.fastcraft.dependencies.bukkit.player.BukkitModulePlayer
import net.benwoodworth.fastcraft.dependencies.bukkit.server.BukkitModuleServer
import net.benwoodworth.fastcraft.dependencies.bukkit.text.BukkitModuleText
import org.bukkit.plugin.java.JavaPlugin

/**
 * Bukkit implementation of FastCraft.
 */
class BukkitFastCraft : FastCraftImplementation, JavaPlugin() {

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
    }
}
