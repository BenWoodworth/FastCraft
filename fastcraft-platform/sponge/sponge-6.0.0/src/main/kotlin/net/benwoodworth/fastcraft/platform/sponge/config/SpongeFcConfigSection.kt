package net.benwoodworth.fastcraft.platform.sponge.config

import net.benwoodworth.fastcraft.platform.api.config.FcConfigSection
import net.benwoodworth.fastcraft.util.Adapter
import ninja.leaping.configurate.ConfigurationNode
import org.spongepowered.api.util.TypeTokens

/**
 * Sponge implementation of [FcConfigSection].
 */
class SpongeFcConfigSection(
        override val base: ConfigurationNode
) : FcConfigSection, Adapter<ConfigurationNode>() {

    override fun hasKey(key: String): Boolean {
        return !base.getNode(key).isVirtual
    }

    override fun getSection(key: String): FcConfigSection {
        return SpongeFcConfigSection(base.getNode(key))
    }

    override fun removeSection(key: String) {
        base.removeChild(key)
    }

    override fun removeValue(key: String) {
        base.removeChild(key)
    }

    override fun getString(key: String): String? {
        return base.getNode(key).string
    }

    override fun setString(key: String, value: String?) {
        base.getNode(key).value = value
    }

    override fun getStringList(key: String): List<String>? {
        val node = base.getNode(key)
        return if (node.isVirtual) {
            null
        } else {
            node.getList(TypeTokens.STRING_TOKEN)
        }
    }

    override fun setStringList(key: String, value: List<String>?) {
        base.getNode(key).value = value
    }

    override fun getInt(key: String): Int? {
        val node = base.getNode(key)
        return if (node.isVirtual) {
            null
        } else {
            node.int
        }
    }

    override fun setInt(key: String, value: Int?) {
        base.getNode(key).setValue(TypeTokens.INTEGER_TOKEN, value)
    }

    override fun getIntList(key: String): List<Int>? {
        val node = base.getNode(key)
        return if (node.isVirtual) {
            null
        } else {
            node.getList(TypeTokens.INTEGER_TOKEN)
        }
    }

    override fun setIntList(key: String, value: List<Int>?) {
        base.getNode(key).value = value
    }

    override fun getBoolean(key: String): Boolean? {
        val node = base.getNode(key)
        return if (node.isVirtual) {
            null
        } else {
            node.boolean
        }
    }

    override fun setBoolean(key: String, value: Boolean?) {
        base.getNode(key).value = value
    }
}
