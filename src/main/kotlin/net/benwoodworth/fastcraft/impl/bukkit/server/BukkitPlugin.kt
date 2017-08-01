package net.benwoodworth.fastcraft.impl.bukkit.server

import net.benwoodworth.fastcraft.dependencies.server.Logger
import net.benwoodworth.fastcraft.dependencies.server.Plugin
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.plugin.java.JavaPlugin
import java.nio.file.Path
import java.nio.file.Paths

/**
 * Bukkit implementation of [Plugin].
 */
class BukkitPlugin(
        plugin: JavaPlugin
) : Plugin, Adapter<JavaPlugin>(plugin) {

    override val logger: Logger
        get() = BukkitLogger(base.logger)

    override val pluginDirectory: Path
        get() = base.dataFolder.toPath()

    override val configFile: Path
        get() = Paths.get(base.dataFolder.absolutePath, "config.yml")
}
