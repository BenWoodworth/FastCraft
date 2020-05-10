package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class BukkitFcConfig_1_7(
    override val configSection: YamlConfiguration,
    private val configFactory: FcConfig.Factory,
) : BukkitFcConfig, FcConfigNode by configFactory.createNode(configSection) {
    override var headerComment: String?
        get() = configSection.options().header()
        set(value) {
            configSection.options().header(value)
        }

    override fun save(file: Path) {
        configSection.save(file.toFile())
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

        override fun createNode(key: String, configSection: ConfigurationSection): FcConfigNode {
            return BukkitFcConfigNode_1_7(key, configSection, configFactory.get())
        }
    }
}
