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

    override fun remove(key: String) {
        base.removeChild(key)
    }

    override fun getString(key: String): String? {
        return base.getNode(key).string
    }

    override fun setString(key: String, value: String) {
        base.getNode(key).setValue(TypeTokens.STRING_TOKEN, value)
    }
}
