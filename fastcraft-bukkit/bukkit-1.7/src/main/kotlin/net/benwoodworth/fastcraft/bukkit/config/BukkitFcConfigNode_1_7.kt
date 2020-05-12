package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.file.YamlConfiguration

class BukkitFcConfigNode_1_7(
    override val config: YamlConfiguration,
    private val path: String,
    private val configFactory: FcConfig.Factory,
) : BukkitFcConfigNode {
    override fun get(key: String): FcConfigNode {
        return configFactory.createNode(config, "$path.$key")
    }

    override fun set(key: String, value: Any?) {
        config.set(key, value)
    }

    override fun set(value: Any?) {
        config.set(path, value)
    }

    override fun getString(): String? {
        return config.getString(path)
    }

    override fun getStringList(): List<String?>? {
        return config.getStringList(path)
    }

    override fun getInt(): Int? {
        return config.getInt(path)
    }

    override fun getIntList(): List<Int?>? {
        return config.getIntegerList(path)
    }

    override fun getDouble(): Double? {
        return config.getDouble(path)
    }

    override fun getDoubleList(): List<Double?>? {
        return config.getDoubleList(path)
    }

    override fun getBoolean(): Boolean? {
        return config.getBoolean(path)
    }

    override fun getBooleanList(): List<Boolean?>? {
        return config.getBooleanList(path)
    }
}
