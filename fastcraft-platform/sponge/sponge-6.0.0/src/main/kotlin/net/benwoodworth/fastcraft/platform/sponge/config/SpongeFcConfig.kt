package net.benwoodworth.fastcraft.platform.sponge.config

import net.benwoodworth.fastcraft.platform.api.config.FcConfig
import net.benwoodworth.fastcraft.platform.api.config.FcConfigSection
import net.benwoodworth.fastcraft.util.Adapter
import ninja.leaping.configurate.commented.CommentedConfigurationNode

/**
 * Sponge implementation of [FcConfig].
 */
class SpongeFcConfig(override val base: CommentedConfigurationNode) :
        FcConfigSection by SpongeFcConfigSection(base),
        Adapter<CommentedConfigurationNode>(),
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
