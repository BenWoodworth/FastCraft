package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import org.bukkit.configuration.file.YamlConfiguration

interface BukkitFcConfig : FcConfig {

    val config: YamlConfiguration
}
