package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfig
import net.benwoodworth.fastcraft.platform.config.FcConfigNode
import org.bukkit.configuration.file.YamlConfiguration

class BukkitFcConfigNode_1_7(
    override val config: YamlConfiguration,
    override val path: String,
    private val fcConfigFactory: FcConfig.Factory,
) : BukkitFcConfigNode {
    override fun getChildKeys(): Set<String> {
        return config.getConfigurationSection(path)?.getKeys(false) ?: emptySet()
    }

    override fun get(key: String): FcConfigNode {
        return fcConfigFactory.createNode(config, "$path.$key")
    }

    override fun set(value: Any?) {
        config.set(path, value)
    }

    override fun getString(): String? {
        return config
            .takeIf { it.contains(path) }
            ?.getString(path)
    }

    override fun getStringList(): List<String?>? {
        return config
            .takeIf { it.contains(path) }
            ?.getStringList(path)
    }

    override fun getInt(): Int? {
        return config
            .takeIf { it.contains(path) }
            ?.getInt(path)
    }

    override fun getIntList(): List<Int?>? {
        return config
            .takeIf { it.contains(path) }
            ?.getIntegerList(path)
    }

    override fun getDouble(): Double? {
        return config
            .takeIf { it.contains(path) }
            ?.getDouble(path)
    }

    override fun getDoubleList(): List<Double?>? {
        return config
            .takeIf { it.contains(path) }
            ?.getDoubleList(path)
    }

    override fun getBoolean(): Boolean? {
        return config
            .takeIf { it.contains(path) }
            ?.getBoolean(path)
    }

    override fun getBooleanList(): List<Boolean?>? {
        return config
            .takeIf { it.contains(path) }
            ?.getBooleanList(path)
    }
}
