package net.benwoodworth.fastcraft.implementations.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.FcLogger
import net.benwoodworth.fastcraft.dependencies.server.FcPlugin
import net.benwoodworth.fastcraft.util.Adapter
import java.nio.file.Path
import org.bukkit.plugin.Plugin as Bukkit_Plugin

/**
 * Bukkit implementation of [FcPlugin].
 */
class BukkitFcPlugin(
        plugin: Bukkit_Plugin
) : FcPlugin, Adapter<Bukkit_Plugin>(plugin) {

    override val logger: FcLogger
        get() = BukkitFcLogger(base.logger)

    override val pluginDirectory: Path
        get() = base.dataFolder.toPath()

    override val configFileName = "config.yml"
}
