package net.benwoodworth.fastcraft.impl.bukkit

import dagger.Component
import net.benwoodworth.fastcraft.dependencies.FastCraftComponent
import net.benwoodworth.fastcraft.impl.bukkit.config.BukkitModuleConfig
import net.benwoodworth.fastcraft.impl.bukkit.event.BukkitModuleEvent
import net.benwoodworth.fastcraft.impl.bukkit.gui.BukkitModuleGui
import net.benwoodworth.fastcraft.impl.bukkit.item.BukkitModuleItem
import net.benwoodworth.fastcraft.impl.bukkit.player.BukkitModulePlayer
import net.benwoodworth.fastcraft.impl.bukkit.server.BukkitModuleServer
import net.benwoodworth.fastcraft.impl.bukkit.text.BukkitModuleText
import javax.inject.Singleton

/**
 * Bukkit implementation of [FastCraftComponent].
 */
@Singleton @Component(modules = arrayOf(
        BukkitModuleConfig::class,
        BukkitModuleEvent::class,
        BukkitModuleGui::class,
        BukkitModuleItem::class,
        BukkitModulePlayer::class,
        BukkitModuleServer::class,
        BukkitModuleText::class
))
interface BukkitFastCraftComponent : FastCraftComponent
