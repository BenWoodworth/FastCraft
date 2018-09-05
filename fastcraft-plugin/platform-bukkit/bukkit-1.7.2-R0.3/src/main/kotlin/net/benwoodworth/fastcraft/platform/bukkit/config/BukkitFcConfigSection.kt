package net.benwoodworth.fastcraft.platform.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigSection
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.configuration.ConfigurationSection

/**
 * Bukkit implementation of [FcConfigSection].
 */
class BukkitFcConfigSection(
        override val base: ConfigurationSection
) : FcConfigSection, Adapter<ConfigurationSection>() {

    override fun hasKey(key: String): Boolean {
        return base.getKeys(false).contains(key)
    }

    override fun getSection(key: String): FcConfigSection {
        return BukkitFcConfigSection(base.getConfigurationSection(key))
    }

    override fun removeSection(key: String) {
        base.set(key, null)
    }

    override fun removeValue(key: String) {
        base.set(key, null)
    }

    override fun getString(key: String): String? {
        return base.getString(key, null)
    }

    override fun setString(key: String, value: String?) {
        base.set(key, value)
    }

    override fun getStringList(key: String): List<String>? {
        return base.takeIf { hasKey(key) }?.getStringList(key)
    }

    override fun setStringList(key: String, value: List<String>?) {
        base.set(key, value)
    }

    override fun getInt(key: String): Int? {
        return base.takeIf { hasKey(key) }?.getInt(key)
    }

    override fun setInt(key: String, value: Int?) {
        base.set(key, value)
    }

    override fun getIntList(key: String): List<Int>? {
        return base.takeIf { hasKey(key) }?.getIntegerList(key)?.toList()
    }

    override fun setIntList(key: String, value: List<Int>?) {
        base.set(key, value)
    }

    override fun getBoolean(key: String): Boolean? {
        return base.takeIf { hasKey(key) }?.getBoolean(key)
    }

    override fun setBoolean(key: String, value: Boolean?) {
        base.set(key, value)
    }
}
