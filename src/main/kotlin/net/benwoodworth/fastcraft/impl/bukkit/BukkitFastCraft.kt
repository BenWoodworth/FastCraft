package net.benwoodworth.fastcraft.impl.bukkit

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.dependencies.FastCraftImplementation
import net.benwoodworth.fastcraft.impl.bukkit.config.BukkitModuleConfig
import net.benwoodworth.fastcraft.impl.bukkit.event.BukkitModuleEvent
import net.benwoodworth.fastcraft.impl.bukkit.gui.BukkitModuleGui
import net.benwoodworth.fastcraft.impl.bukkit.item.BukkitModuleItem
import net.benwoodworth.fastcraft.impl.bukkit.player.BukkitModulePlayer
import net.benwoodworth.fastcraft.impl.bukkit.server.BukkitModuleServer
import net.benwoodworth.fastcraft.impl.bukkit.text.BukkitModuleText
import org.bukkit.plugin.java.JavaPlugin

/**
 * Bukkit implementation of FastCraft.
 */
class BukkitFastCraft : FastCraftImplementation, JavaPlugin() {

    override lateinit var instance: FastCraft
        private set

    @Suppress("DEPRECATION") // TODO Don't suppress deprecation
    override fun onEnable() {
        val fastCraftBuilder = DaggerBukkitFastCraftComponent.builder()
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
