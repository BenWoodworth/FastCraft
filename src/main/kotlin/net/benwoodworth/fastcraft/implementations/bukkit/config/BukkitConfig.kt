package net.benwoodworth.fastcraft.implementations.bukkit.config

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
            var lines = base.options().header()?.split('\n') ?: emptyList()

            if (lines.takeIf { it.isNotEmpty() }?.last() == "") {
                lines = lines.take(lines.size - 1)
            }

            return lines.map {
                when {
                    it.isNotEmpty() && it[0] == ' ' -> it.substring(1)
                    else -> it
                }
            }
        }
        set(value) {
            base.options().header(value
                    .takeUnless { it.isEmpty() }
                    ?.map { " $it" }
                    ?.joinToString("\n")
            )
        }
}
