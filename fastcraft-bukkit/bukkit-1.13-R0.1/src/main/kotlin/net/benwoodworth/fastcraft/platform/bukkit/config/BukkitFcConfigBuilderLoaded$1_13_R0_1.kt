package net.benwoodworth.fastcraft.platform.bukkit.config

import net.benwoodworth.fastcraft.platform.api.config.FcConfigBuilderLoaded
import org.bukkit.configuration.file.YamlConfiguration

inline class `BukkitFcConfigBuilderLoaded$1_13_R0_1`(
    private val loadConfig: () -> YamlConfiguration
) : FcConfigBuilderLoaded {

    override fun build() = `BukkitFcConfig$1_13_R0_1`(loadConfig())
}
