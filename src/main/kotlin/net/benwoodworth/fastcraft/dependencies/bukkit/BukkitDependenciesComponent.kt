package net.benwoodworth.fastcraft.dependencies.bukkit

import dagger.Component
import net.benwoodworth.fastcraft.dependencies.abstractions.DependenciesComponent
import net.benwoodworth.fastcraft.dependencies.bukkit.config.BukkitModuleConfig
import net.benwoodworth.fastcraft.dependencies.bukkit.event.BukkitModuleEvent
import net.benwoodworth.fastcraft.dependencies.bukkit.gui.BukkitModuleGui
import net.benwoodworth.fastcraft.dependencies.bukkit.item.BukkitModuleItem
import net.benwoodworth.fastcraft.dependencies.bukkit.player.BukkitModulePlayer
import net.benwoodworth.fastcraft.dependencies.bukkit.server.BukkitModuleServer
import net.benwoodworth.fastcraft.dependencies.bukkit.text.BukkitModuleText
import javax.inject.Singleton

/**
 * Bukkit implementation of [DependenciesComponent].
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
interface BukkitDependenciesComponent : DependenciesComponent
