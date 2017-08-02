package net.benwoodworth.fastcraft.impl.bukkit.config

import net.benwoodworth.fastcraft.dependencies.config.Config
import net.benwoodworth.fastcraft.dependencies.config.ConfigSection
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.configuration.file.YamlConfiguration

/**
 * Bukkit implementation of [Config].
 */
class BukkitConfig(baseSection: YamlConfiguration) :
        ConfigSection by BukkitConfigSection(baseSection),
        Adapter<YamlConfiguration>(baseSection),
        Config {

    override var header: List<String>
        get() {
            val lines = base.options().header()?.split('\n') ?: emptyList()

            return lines.map {
                when {
                    it.isNotEmpty() && it[0] == ' ' -> it.substring(1)
                    else -> it
                }
            }
        }
        set(value) {
            base.options().header(value
                    .takeIf { it.isEmpty() }
                    ?.map { " $it" }
                    ?.joinToString("\n")
            )
        }
}
