package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.config

import net.benwoodworth.fastcraft.platform.config.FcConfigEntry
import org.bukkit.configuration.file.YamlConfiguration
import java.nio.file.Path

class BukkitFcConfig(
    private val config: YamlConfiguration
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
        return BukkitFcConfigEntry(key, config, null)
    }
}
