package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.file.YamlConfiguration

interface FcConfig_Bukkit : FcConfig, FcConfigNode_Bukkit {
    interface Factory : FcConfig.Factory {
        fun create(config: YamlConfiguration): FcConfig

        fun createNode(config: YamlConfiguration, path: String): FcConfigNode
    }
}

fun FcConfig.Factory.create(config: YamlConfiguration): FcConfig {
    return (this as FcConfig_Bukkit.Factory).create(config)
}

fun FcConfig.Factory.createNode(config: YamlConfiguration, path: String): FcConfigNode {
    return (this as FcConfig_Bukkit.Factory).createNode(config, path)
}
