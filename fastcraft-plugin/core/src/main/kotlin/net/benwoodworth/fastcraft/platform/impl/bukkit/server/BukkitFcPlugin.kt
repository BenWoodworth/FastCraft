package net.benwoodworth.fastcraft.implementations.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.FcLogger
import net.benwoodworth.fastcraft.dependencies.server.FcPlugin
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.plugin.Plugin
import java.nio.file.Path

/**
 * Bukkit implementation of [FcPlugin].
 */
class BukkitFcPlugin(
        override val base: Plugin
) : FcPlugin, Adapter<Plugin>() {

    override val logger: FcLogger
        get() = BukkitFcLogger(base.logger)

    override val pluginDirectory: Path
        get() = base.dataFolder.toPath()

    override val configFileName = "config.yml"
}
