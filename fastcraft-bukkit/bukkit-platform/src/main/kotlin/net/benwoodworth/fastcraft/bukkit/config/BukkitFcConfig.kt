package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration

interface BukkitFcConfig : FcConfig {
    val config: YamlConfiguration

    interface Factory : FcConfig.Factory {
        fun create(config: YamlConfiguration): FcConfig

        fun createNode(key: String, configSection: ConfigurationSection): FcConfigNode
    }
}

val FcConfig.config: YamlConfiguration
    get() = (this as BukkitFcConfig).config

fun FcConfig.Factory.create(config: YamlConfiguration): FcConfig {
    return (this as BukkitFcConfig.Factory).create(config)
}

fun FcConfig.Factory.createNode(key: String, configSection: ConfigurationSection): FcConfigNode {
    return (this as BukkitFcConfig.Factory).createNode(key, configSection)
}
