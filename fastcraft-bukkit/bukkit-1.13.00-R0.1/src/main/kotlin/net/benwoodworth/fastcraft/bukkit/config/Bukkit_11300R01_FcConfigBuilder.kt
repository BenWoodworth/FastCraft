package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigBuilder
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path

object Bukkit_11300R01_FcConfigBuilder : FcConfigBuilder {

    override fun empty() = Bukkit_11300R01_FcConfigBuilderLoaded {
        YamlConfiguration()
    }

    override fun file(file: Path) = Bukkit_11300R01_FcConfigBuilderLoaded {
        YamlConfiguration.loadConfiguration(file.toFile())
    }
}
