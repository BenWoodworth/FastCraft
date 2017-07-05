package net.benwoodworth.fastcraft.sponge.dependencies.config

import net.benwoodworth.fastcraft.core.dependencies.config.Config
import net.benwoodworth.fastcraft.core.dependencies.config.ConfigManager
import ninja.leaping.configurate.hocon.HoconConfigurationLoader
import java.nio.file.Path

/**
 * Sponge implementation of [ConfigManager].
 */
class SpongeConfigManager : ConfigManager {

    override fun getEmptyConfig(): Config {
        val loader = HoconConfigurationLoader.builder().build()
        return SpongeConfigAdapter(loader.load())
    }

    override fun loadConfig(path: Path): Config {
        val loader = HoconConfigurationLoader.builder()
                .setPath(path)
                .build()

        return SpongeConfigAdapter(loader.load())
    }

    override fun saveConfig(path: Path, config: Config) {
        val loader = HoconConfigurationLoader.builder()
                .setPath(path)
                .build()

        loader.save((config as SpongeConfigAdapter).base)
    }
}
