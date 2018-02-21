package net.benwoodworth.fastcraft.implementations.sponge.config

import net.benwoodworth.fastcraft.dependencies.config.FcConfig
import net.benwoodworth.fastcraft.dependencies.config.FcConfigSection
import net.benwoodworth.fastcraft.util.Adapter
import ninja.leaping.configurate.commented.CommentedConfigurationNode

/**
 * Sponge implementation of [FcConfig].
 */
class SpongeFcConfig(baseNode: CommentedConfigurationNode) :
        FcConfigSection by SpongeFcConfigSection(baseNode),
        Adapter<CommentedConfigurationNode>(baseNode),
        FcConfig {

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
