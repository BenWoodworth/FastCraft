package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigBuilder
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path

@Suppress("ClassName")
object Bukkit_11300R01_FcConfigBuilder : FcConfigBuilder, FcConfigBuilder.Loaded {

    private lateinit var load: () -> YamlConfiguration

    override fun empty(): FcConfigBuilder.Loaded {
        load = { YamlConfiguration() }
        return this
    }

    override fun fromPath(value: Path): FcConfigBuilder.Loaded {
        load = { YamlConfiguration.loadConfiguration(value.toFile()) }
        return this
    }

    override fun build(): FcConfig {
        return Bukkit_11300R01_FcConfig(load())
    }
}
