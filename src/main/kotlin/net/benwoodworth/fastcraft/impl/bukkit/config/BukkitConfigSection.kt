package net.benwoodworth.fastcraft.impl.bukkit.config

import net.benwoodworth.fastcraft.dependencies.config.ConfigSection
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.configuration.ConfigurationSection

/**
 * Bukkit implementation of [ConfigSection].
 */
class BukkitConfigSection(
        baseSection: ConfigurationSection
) : ConfigSection, Adapter<ConfigurationSection>(baseSection) {

    override fun getSection(key: String): ConfigSection {
        return BukkitConfigSection(base.getConfigurationSection(key))
    }

    override fun removeSection(key: String) {
        base.set(key, null)
    }

    override fun removeValue(key: String) {
        base.set(key, null)
    }

    override fun getString(key: String): String? {
        return base.getString(key)
    }

    override fun setString(key: String, value: String) {
        base.set(key, value)
    }

    override fun getStringList(key: String): List<String>? {
        return base.getStringList(key).toList()
    }

    override fun setStringList(key: String, value: List<String>) {
        base.set(key, value)
    }

    override fun getInt(key: String): Int? {
        return base.getInt(key)
    }

    override fun setInt(key: String, value: Int) {
        base.set(key, value)
    }

    override fun getIntList(key: String): List<Int>? {
        return base.getIntegerList(key).toList()
    }

    override fun setIntList(key: String, value: List<Int>) {
        base.set(key, value)
    }

    override fun getBoolean(key: String): Boolean? {
        return base.getBoolean(key)
    }

    override fun setBoolean(key: String, value: Boolean) {
        base.set(key, value)
    }
}
