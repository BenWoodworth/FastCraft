package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigFactory
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path

@Suppress("ClassName")
object Bukkit_11300R01_FcConfigFactory : FcConfigFactory {

    override fun createEmpty(): FcConfig {
        return Bukkit_11300R01_FcConfig(YamlConfiguration())
    }

    override fun createFromPath(value: Path): FcConfig {
        return Bukkit_11300R01_FcConfig(
            YamlConfiguration.loadConfiguration(value.toFile())
        )
    }
}
