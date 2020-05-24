package net.benwoodworth.fastcraft.bukkit.util

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import java.io.InputStream

fun InputStream.toYamlConfiguration(): YamlConfiguration {
    return this.use { YamlConfiguration.loadConfiguration(this.reader()) }
}

inline fun <R> ConfigurationSection.getMap(
    path: String,
    getValue: ConfigurationSection.(key: String) -> R,
): Map<String, R>? {
    return getConfigurationSection(path)
        ?.let { section ->
            section
                .getKeys(false)
                .map { key -> key to section.getValue(key) }
        }
        ?.toMap()
}
