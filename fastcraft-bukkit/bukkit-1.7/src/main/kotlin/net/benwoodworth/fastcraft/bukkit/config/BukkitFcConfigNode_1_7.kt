package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.ConfigurationSection

class BukkitFcConfigNode_1_7(
    private val key: String,
    override val configSection: ConfigurationSection,
    private val configFactory: FcConfig.Factory,
) : BukkitFcConfigNode {
    override fun get(key: String): FcConfigNode {
        return configFactory.createNode(configSection.getConfigurationSection(key))
    }

    override fun set(key: String, value: Any?) {
        configSection.parent.set(key, value)
    }

    override fun createNode(): FcConfigNode {
        configSection.parent.createSection(key)
        return this
    }

    override fun removeNode() {
        configSection.parent.set(key, null)
    }

    override fun getString(): String? {
        return configSection.parent.getString(key)
    }

    override fun getStringList(): List<String?>? {
        return configSection.parent.getStringList(key)
    }

    override fun getInt(): Int? {
        return configSection.parent.getInt(key)
    }

    override fun getIntList(): List<Int?>? {
        return configSection.parent.getIntegerList(key)
    }

    override fun getDouble(): Double? {
        return configSection.parent.getDouble(key)
    }

    override fun getDoubleList(): List<Double?>? {
        return configSection.parent.getDoubleList(key)
    }

    override fun getBoolean(): Boolean? {
        return configSection.parent.getBoolean(key)
    }

    override fun getBooleanList(): List<Boolean?>? {
        return configSection.parent.getBooleanList(key)
    }
}
