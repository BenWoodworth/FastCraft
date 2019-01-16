package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigFactory
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path

class BukkitFcConfigFactory : FcConfigFactory {

    override fun createEmpty(): FcConfig {
        return BukkitFcConfig(YamlConfiguration())
    }

    override fun createFromPath(value: Path): FcConfig {
        return BukkitFcConfig(
            YamlConfiguration.loadConfiguration(value.toFile())
        )
    }
}
