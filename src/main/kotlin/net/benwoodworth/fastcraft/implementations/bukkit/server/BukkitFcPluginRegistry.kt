package net.benwoodworth.fastcraft.implementations.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.FcPlugin
import net.benwoodworth.fastcraft.dependencies.server.FcPluginRegistry
import org.bukkit.Bukkit

/**
 * Bukkit implementation of [FcPluginRegistry].
 */
class BukkitFcPluginRegistry : FcPluginRegistry {

    override fun getPlugin(name: String): FcPlugin? {
        return Bukkit.getPluginManager().getPlugin(name)
                ?.let(::BukkitFcPlugin)
    }
}