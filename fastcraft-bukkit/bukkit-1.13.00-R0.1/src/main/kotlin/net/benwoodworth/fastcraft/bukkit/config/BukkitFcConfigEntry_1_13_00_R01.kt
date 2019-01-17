package net.benwoodworth.fastcraft.bukkit.config

import net.benwoodworth.fastcraft.platform.config.FcConfigEntry
import org.bukkit.configuration.Configuration
import org.bukkit.configuration.ConfigurationSection

class BukkitFcConfigEntry_1_13_00_R01(
    key: String,
    private val config: Configuration,
    private val parentEntry: BukkitFcConfigEntry?
) : BukkitFcConfigEntry {

    private val escapedKey = key.escapeKey()

    override var string: String?
        get() = getParentSection().getString(escapedKey)
        set(value) = setValue(value)

    override var stringList: List<String?>?
        get() = getParentSection().getStringList(escapedKey)
        set(value) = setValue(value)

    override var int: Int?
        get() = getParentSection().getInt(escapedKey)
        set(value) = setValue(value)

    override var intList: List<Int?>?
        get() = getParentSection().getIntegerList(escapedKey)
        set(value) = setValue(value)

    override var double: Double?
        get() = getParentSection().getDouble(escapedKey)
        set(value) = setValue(value)

    override var doubleList: List<Double?>?
        get() = getParentSection().getDoubleList(escapedKey)
        set(value) = setValue(value)

    override var boolean: Boolean?
        get() = getParentSection().getBoolean(escapedKey)
        set(value) = setValue(value)

    override var booleanList: List<Boolean?>?
        get() = getParentSection().getBooleanList(escapedKey)
        set(value) = setValue(value)

    override fun remove() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(key: String): FcConfigEntry {
        return BukkitFcConfigEntry_1_13_00_R01(key, config, this)
    }

    private fun getParentSection(): ConfigurationSection {
        return parentEntry?.getSection() ?: config
    }

    private fun getSection(): ConfigurationSection {
        TODO()
    }

    private fun setValue(value: Any?) {
        getParentSection().set(escapedKey, value)
    }

    private fun String.escapeKey(): String {
        return when {
            isEmpty() -> "[]"
            startsWith('[') && endsWith(']') || contains('.') -> "[$this]"
            else -> this
        }
    }

    private fun String.unescapeKey(): String {
        return when {
            length < 2 -> this
            startsWith('[') && endsWith(']') -> substring(1, length - 2)
            else -> this
        }
    }
}
