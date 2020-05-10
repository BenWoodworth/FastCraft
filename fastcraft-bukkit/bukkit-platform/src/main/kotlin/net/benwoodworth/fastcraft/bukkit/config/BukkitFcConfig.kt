package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration

interface BukkitFcConfig : FcConfig, BukkitFcConfigNode {
    override val configSection: YamlConfiguration

    interface Factory : FcConfig.Factory {
        fun create(config: YamlConfiguration): FcConfig

        fun createNode(key: String, configSection: ConfigurationSection): FcConfigNode
    }
}

fun FcConfig.Factory.create(config: YamlConfiguration): FcConfig {
    return (this as BukkitFcConfig.Factory).create(config)
}

fun FcConfig.Factory.createNode(configSection: ConfigurationSection): FcConfigNode {
    return (this as BukkitFcConfig.Factory).createNode(configSection)
}
