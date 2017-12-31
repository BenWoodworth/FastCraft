package net.benwoodworth.fastcraft.implementations.sponge.config

import net.benwoodworth.fastcraft.dependencies.config.Config
import net.benwoodworth.fastcraft.dependencies.config.ConfigManager
import ninja.leaping.configurate.hocon.HoconConfigurationLoader
import java.nio.file.Path

/**
 * Sponge implementation of [ConfigManager].
 */
class SpongeConfigManager : ConfigManager {

    override fun getEmptyConfig(): Config {
        val loader = HoconConfigurationLoader.builder().build()
        return SpongeConfig(loader.load())
    }

    override fun loadConfig(path: Path): Config {
        val loader = HoconConfigurationLoader.builder()
                .setPath(path)
                .build()

        return SpongeConfig(loader.load())
    }

    override fun saveConfig(config: Config, path: Path) {
        config as SpongeConfig

        val loader = HoconConfigurationLoader.builder()
                .setDefaultOptions(config.configOptions)
                .setPath(path)
                .build()

        loader.save(config.base)
    }

    override fun getFileExtension() = ".conf"
}
