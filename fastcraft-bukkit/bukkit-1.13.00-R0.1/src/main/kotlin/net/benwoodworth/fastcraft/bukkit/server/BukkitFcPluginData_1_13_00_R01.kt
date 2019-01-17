package net.benwoodworth.fastcraft.bukkit.server

import org.bukkit.plugin.Plugin
import java.nio.file.Path

class BukkitFcPluginData_1_13_00_R01(
    plugin: Plugin
) : BukkitFcPluginData {

    override val dataFolder: Path = plugin.dataFolder.toPath()

    override val configFile: Path = dataFolder.resolve("config.yml")
}
