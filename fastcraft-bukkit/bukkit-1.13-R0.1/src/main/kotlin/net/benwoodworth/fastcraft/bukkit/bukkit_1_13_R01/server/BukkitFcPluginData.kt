package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.server

import org.bukkit.plugin.Plugin
import java.nio.file.Path

class BukkitFcPluginData(
    plugin: Plugin
) : BukkitFcPluginData {

    override val dataFolder: Path = plugin.dataFolder.toPath()

    override val configFile: Path = dataFolder.resolve("config.yml")
}
