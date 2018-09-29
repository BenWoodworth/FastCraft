package net.benwoodworth.fastcraft.platform.bukkit.server

import net.benwoodworth.fastcraft.platform.api.server.FcPluginRegistry
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
