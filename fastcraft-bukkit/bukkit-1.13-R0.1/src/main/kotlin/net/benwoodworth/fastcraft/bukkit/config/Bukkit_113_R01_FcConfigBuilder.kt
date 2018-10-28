package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigBuilder
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path

object Bukkit_113_R01_FcConfigBuilder : FcConfigBuilder {

    override fun empty() = Bukkit_113_R01_FcConfigBuilderLoaded {
        YamlConfiguration()
    }

    override fun file(file: Path) = Bukkit_113_R01_FcConfigBuilderLoaded {
        YamlConfiguration.loadConfiguration(file.toFile())
    }
}
