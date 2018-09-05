package net.benwoodworth.fastcraft.platform.impl.sponge.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigManager
import ninja.leaping.configurate.hocon.HoconConfigurationLoader
import java.nio.file.Path

/**
 * Sponge implementation of [FcConfigManager].
 */
class SpongeFcConfigManager : FcConfigManager {

    override fun getEmptyConfig(): FcConfig {
        val loader = HoconConfigurationLoader.builder().build()
        return SpongeFcConfig(loader.load())
    }

    override fun loadConfig(path: Path): FcConfig {
        val loader = HoconConfigurationLoader.builder()
                .setPath(path)
                .build()

        return SpongeFcConfig(loader.load())
    }

    override fun saveConfig(config: FcConfig, path: Path) {
        config as SpongeFcConfig

        val loader = HoconConfigurationLoader.builder()
                .setDefaultOptions(config.configOptions)
                .setPath(path)
                .build()

        loader.save(config.base)
    }

    override fun getFileExtension() = ".conf"
}
