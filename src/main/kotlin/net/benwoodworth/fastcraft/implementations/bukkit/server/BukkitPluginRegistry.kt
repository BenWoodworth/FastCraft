package net.benwoodworth.fastcraft.implementations.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.Plugin
import net.benwoodworth.fastcraft.dependencies.server.PluginRegistry
import org.bukkit.Bukkit

/**
 * Bukkit implementation of [PluginRegistry].
 */
class BukkitPluginRegistry : PluginRegistry {

    override fun getPlugin(name: String): Plugin? {
        return Bukkit.getPluginManager().getPlugin(name)
                ?.let(::BukkitPlugin)
    }
}