package net.benwoodworth.fastcraft.sponge.dependencies.config

import net.benwoodworth.fastcraft.core.dependencies.config.ConfigSection
import net.benwoodworth.fastcraft.core.util.Adapter
import ninja.leaping.configurate.ConfigurationNode
import org.spongepowered.api.util.TypeTokens

/**
 * Sponge implementation of [ConfigSection].
 */
class SpongeConfigSectionAdapter(
        baseNode: ConfigurationNode
) : ConfigSection, Adapter<ConfigurationNode>(baseNode) {

    override fun getSection(key: String): ConfigSection {
        return SpongeConfigSectionAdapter(base.getNode(key))
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

    override fun setString(key: String, value: String) {
        base.getNode(key).setValue(TypeTokens.STRING_TOKEN, value)
    }

    override fun getInt(key: String): Int? {
        return base.getNode(key).int
    }

    override fun setInt(key: String, value: Int) {
        base.getNode(key).setValue(TypeTokens.INTEGER_TOKEN, value)
    }

    override fun getBoolean(key: String): Boolean? {
        return base.getNode(key).boolean
    }

    override fun setBoolean(key: String, value: Boolean) {
        base.getNode(key).setValue(TypeTokens.BOOLEAN_TOKEN, value)
    }
}
