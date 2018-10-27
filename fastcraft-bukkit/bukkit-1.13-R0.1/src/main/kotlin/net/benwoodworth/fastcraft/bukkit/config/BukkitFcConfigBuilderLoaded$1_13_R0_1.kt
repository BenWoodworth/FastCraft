package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigBuilderLoaded
import org.bukkit.configuration.file.YamlConfiguration

class `BukkitFcConfigBuilderLoaded$1_13_R0_1`(
    private val loadConfig: () -> YamlConfiguration
) : FcConfigBuilderLoaded {

    override fun build() = `BukkitFcConfig$1_13_R0_1`(loadConfig())
}
