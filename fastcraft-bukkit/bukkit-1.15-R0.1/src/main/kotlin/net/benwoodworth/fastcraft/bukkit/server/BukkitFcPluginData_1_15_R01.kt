package net.benwoodworth.fastcraft.bukkit.server

import org.bukkit.plugin.Plugin
import java.nio.file.Path
import javax.inject.Inject

class BukkitFcPluginData_1_15_R01 @Inject constructor(
    plugin: Plugin
) : BukkitFcPluginData {
    override val dataFolder: Path = plugin.dataFolder.toPath()

    override val configFile: Path = dataFolder.resolve("config.yml")
}
