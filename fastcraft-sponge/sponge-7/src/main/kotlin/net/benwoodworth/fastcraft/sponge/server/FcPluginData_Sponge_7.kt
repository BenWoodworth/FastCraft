package net.benwoodworth.fastcraft.sponge.server

import org.spongepowered.api.Sponge
import org.spongepowered.api.asset.AssetManager
import org.spongepowered.api.config.ConfigManager
import org.spongepowered.api.data.DataManager
import org.spongepowered.api.plugin.PluginContainer
import sun.font.FontConfigManager
import java.nio.file.Path
import java.nio.file.Paths
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FcPluginData_Sponge_7 @Inject constructor(
    plugin: PluginContainer,
    configManager: ConfigManager,
) : FcPluginData_Sponge {
    override val dataFolder: Path = configManager.getPluginConfig(plugin).directory

    override val configFile: Path = configManager.getSharedConfig(plugin).configPath
}
