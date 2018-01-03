package net.benwoodworth.fastcraft.implementations.bukkit

import dagger.Component
import net.benwoodworth.fastcraft.dependencies.DependenciesComponent
import net.benwoodworth.fastcraft.implementations.bukkit.config.BukkitModuleConfig
import net.benwoodworth.fastcraft.implementations.bukkit.event.BukkitModuleEvent
import net.benwoodworth.fastcraft.implementations.bukkit.gui.BukkitModuleGui
import net.benwoodworth.fastcraft.implementations.bukkit.item.BukkitModuleItem
import net.benwoodworth.fastcraft.implementations.bukkit.player.BukkitModulePlayer
import net.benwoodworth.fastcraft.implementations.bukkit.server.BukkitModuleServer
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitModuleText
import javax.inject.Singleton

/**
 * Bukkit implementation of [DependenciesComponent].
 */
@Singleton
@Component(modules = [
    BukkitModuleConfig::class,
    BukkitModuleEvent::class,
    BukkitModuleGui::class,
    BukkitModuleItem::class,
    BukkitModulePlayer::class,
    BukkitModuleServer::class,
    BukkitModuleText::class
])
interface BukkitDependenciesComponent : DependenciesComponent
