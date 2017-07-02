package net.benwoodworth.fastcraft.sponge.dependencies.config

import net.benwoodworth.fastcraft.core.dependencies.config.ConfigSection
import net.benwoodworth.fastcraft.core.util.Adapter
import ninja.leaping.configurate.ConfigurationNode

/**
 * Sponge implementation of [ConfigSection].
 */
class SpongeConfigSectionAdapter(
        baseNode: ConfigurationNode
) : ConfigSection, Adapter<ConfigurationNode>(baseNode) {

    override fun getSection(name: String): ConfigSection {
        return SpongeConfigSectionAdapter(base.getNode())
    }

    override fun <T> set(key: String, value: T?) {
        base.getNode(key).value = value
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: String): T? {
        return base.getNode(key).value as T?
    }
}
