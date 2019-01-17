package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path

class BukkitFcConfigFactory : BukkitFcConfigFactory {

    override fun createEmpty(): FcConfig {
        return BukkitFcConfig(YamlConfiguration())
    }

    override fun createFromPath(value: Path): FcConfig {
        return BukkitFcConfig(
            YamlConfiguration.loadConfiguration(value.toFile())
        )
    }
}
