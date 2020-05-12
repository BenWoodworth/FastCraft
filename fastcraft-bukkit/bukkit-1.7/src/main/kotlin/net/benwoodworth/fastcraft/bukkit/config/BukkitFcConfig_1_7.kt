package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class BukkitFcConfig_1_7(
    override val config: YamlConfiguration,
    private val configFactory: FcConfig.Factory,
) : BukkitFcConfig, FcConfigNode by configFactory.createNode(config, "") {
    override var headerComment: String?
        get() = config.options().header()
        set(value) {
            config.options().header(value)
        }

    override fun get(key: String): FcConfigNode {
        return configFactory.createNode(config, key)
    }

    override fun save(file: Path) {
        config.save(file.toFile())
    }

    @Singleton
    class Factory @Inject constructor(
        private val configFactory: Provider<FcConfig.Factory>,
    ) : BukkitFcConfig.Factory {
        override fun create(): FcConfig {
            return create(YamlConfiguration())
        }

        override fun load(file: Path): FcConfig {
            return create(
                YamlConfiguration.loadConfiguration(file.toFile())
            )
        }

        override fun create(config: YamlConfiguration): FcConfig {
            return BukkitFcConfig_1_7(config, configFactory.get())
        }

        override fun createNode(config: YamlConfiguration, path: String): FcConfigNode {
            return BukkitFcConfigNode_1_7(config, path, configFactory.get())
        }
    }
}
