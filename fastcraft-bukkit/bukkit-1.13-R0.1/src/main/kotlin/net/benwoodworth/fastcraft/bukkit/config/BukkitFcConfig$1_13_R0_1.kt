package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigEntry
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path

inline class `BukkitFcConfig$1_13_R0_1`(
    private val config: YamlConfiguration
) : FcConfig {

    override var header: String?
        get() = config.options().header()
        set(value) {
            config.options().header(value)
        }

    override fun save(path: Path) {
        config.save(path.toFile())
    }

    override fun get(key: String): FcConfigEntry {
        return `BukkitFcConfigEntry$1_13_R0_1`(key, config, null)
    }
}
