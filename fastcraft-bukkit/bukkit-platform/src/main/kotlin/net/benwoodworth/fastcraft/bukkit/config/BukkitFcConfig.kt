package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.file.YamlConfiguration

interface BukkitFcConfig : FcConfig, BukkitFcConfigNode {
    interface Factory : FcConfig.Factory {
        fun create(config: YamlConfiguration): FcConfig

        fun createNode(config: YamlConfiguration, path: String): FcConfigNode
    }
}

fun FcConfig.Factory.create(config: YamlConfiguration): FcConfig {
    return (this as BukkitFcConfig.Factory).create(config)
}

fun FcConfig.Factory.createNode(config: YamlConfiguration, path: String): FcConfigNode {
    return (this as BukkitFcConfig.Factory).createNode(config, path)
}
