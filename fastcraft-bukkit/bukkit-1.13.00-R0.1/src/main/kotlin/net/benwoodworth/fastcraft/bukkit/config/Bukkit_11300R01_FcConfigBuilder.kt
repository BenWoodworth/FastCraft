package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigBuilder
import net.benwoodworth.fastcraft.platform.config.FcConfigBuilderLoaded
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path

@Suppress("ClassName")
object Bukkit_11300R01_FcConfigBuilder : FcConfigBuilder {

    override fun empty(): FcConfigBuilderLoaded {
        return Bukkit_11300R01_FcConfigBuilderLoaded {
            YamlConfiguration()
        }
    }

    override fun file(file: Path): Bukkit_11300R01_FcConfigBuilderLoaded {
        return Bukkit_11300R01_FcConfigBuilderLoaded {
            YamlConfiguration.loadConfiguration(file.toFile())
        }
    }
}
