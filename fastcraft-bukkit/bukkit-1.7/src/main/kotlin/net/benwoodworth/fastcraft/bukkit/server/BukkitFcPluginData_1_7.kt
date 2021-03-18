package net.benwoodworth.fastcraft.bukkit.server

import org.bukkit.plugin.Plugin
import java.nio.file.Path
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FcPluginData_Bukkit_1_7 @Inject constructor(
    plugin: Plugin,
) : FcPluginData_Bukkit {
    override val dataFolder: Path = plugin.dataFolder.toPath()

    override val configFile: Path = dataFolder.resolve("config.yml")
}
