package net.benwoodworth.fastcraft.impl.sponge.config

import net.benwoodworth.fastcraft.dependencies.config.Config
import net.benwoodworth.fastcraft.dependencies.config.ConfigSection
import net.benwoodworth.fastcraft.util.Adapter
import ninja.leaping.configurate.commented.CommentedConfigurationNode

/**
 * Sponge implementation of [Config].
 */
class SpongeConfig(baseNode: CommentedConfigurationNode) :
        ConfigSection by SpongeConfigSection(baseNode),
        Adapter<CommentedConfigurationNode>(baseNode),
        Config {

    var configOptions = base.options
        private set

    override var header: List<String>
        get() {
            val lines = configOptions.header?.split('\n') ?: emptyList()

            return lines.map {
                when {
                    it.isNotEmpty() && it[0] == ' ' -> it.substring(1)
                    else -> it
                }
            }
        }
        set(value) {
            configOptions = configOptions.setHeader(value
                    .takeUnless { it.isEmpty() }
                    ?.map { " $it" }
                    ?.joinToString("\n")
            )
        }
}
