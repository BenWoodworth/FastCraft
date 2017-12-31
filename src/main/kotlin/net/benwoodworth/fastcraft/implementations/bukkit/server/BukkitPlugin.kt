package net.benwoodworth.fastcraft.implementations.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.Logger
import net.benwoodworth.fastcraft.dependencies.server.Plugin
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.Bukkit
import java.nio.file.Path
import org.bukkit.plugin.Plugin as Bukkit_Plugin

/**
 * Bukkit implementation of [Plugin].
 */
class BukkitPlugin(
        plugin: Bukkit_Plugin
) : Plugin, Adapter<Bukkit_Plugin>(plugin) {

    override val logger: Logger
        get() = BukkitLogger(base.logger)

    override val pluginDirectory: Path
        get() = base.dataFolder.toPath()

    override val configFileName = "config.yml"

    /**
     * Bukkit implementation of [Plugin.Provider].
     */
    class Provider : Plugin.Provider {

        override fun getPlugin(name: String): Plugin? {
            return Bukkit.getPluginManager().getPlugin(name)
                    ?.let(::BukkitPlugin)
        }
    }
}
