package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigEntry
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path
import javax.inject.Inject
import javax.inject.Singleton

class BukkitFcConfig_1_7(
    override val config: YamlConfiguration,
) : BukkitFcConfig {
    override var header: String?
        get() = config.options().header()
        set(value) {
            config.options().header(value)
        }

    override fun save(path: Path) {
        config.save(path.toFile())
    }

    override fun get(key: String): FcConfigEntry {
        return BukkitFcConfigEntry_1_7(key, config, null)
    }

    @Singleton
    class Factory @Inject constructor(
    ) : BukkitFcConfig.Factory {
        override fun createEmpty(): FcConfig {
            return BukkitFcConfig_1_7(YamlConfiguration())
        }

        override fun createFromPath(value: Path): FcConfig {
            return BukkitFcConfig_1_7(
                YamlConfiguration.loadConfiguration(value.toFile())
            )
        }
    }
}
