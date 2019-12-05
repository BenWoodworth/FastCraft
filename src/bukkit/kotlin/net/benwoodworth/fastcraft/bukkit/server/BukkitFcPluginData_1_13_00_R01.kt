package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.bukkit.BukkitFastCraftPlugin
import java.nio.file.Path
import javax.inject.Inject

class BukkitFcPluginData_1_13_00_R01 @Inject constructor(
    plugin: BukkitFastCraftPlugin
) : BukkitFcPluginData {
    override val dataFolder: Path = plugin.dataFolder.toPath()

    override val configFile: Path = dataFolder.resolve("config.yml")
}
