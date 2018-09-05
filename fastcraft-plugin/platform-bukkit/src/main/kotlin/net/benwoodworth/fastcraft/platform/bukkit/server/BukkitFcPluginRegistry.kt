package net.benwoodworth.fastcraft.platform.impl.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcPlugin
import net.benwoodworth.fastcraft.platform.server.FcPluginRegistry
import org.bukkit.Server
import javax.inject.Inject

/**
 * Bukkit implementation of [FcPluginRegistry].
 */
class BukkitFcPluginRegistry @Inject constructor(
        private val server: Server
) : FcPluginRegistry {

    override fun getPlugin(name: String): FcPlugin? {
        return server.pluginManager
                .getPlugin(name)
                ?.let(::BukkitFcPlugin)
    }
}