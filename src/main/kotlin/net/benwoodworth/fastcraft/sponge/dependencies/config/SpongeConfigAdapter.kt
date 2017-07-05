package net.benwoodworth.fastcraft.sponge.dependencies.config

import net.benwoodworth.fastcraft.core.dependencies.config.Config
import net.benwoodworth.fastcraft.core.dependencies.config.ConfigSection
import net.benwoodworth.fastcraft.core.util.Adapter
import ninja.leaping.configurate.commented.CommentedConfigurationNode

/**
 * Sponge implementation of [Config].
 */
class SpongeConfigAdapter(baseNode: CommentedConfigurationNode) :
        ConfigSection by SpongeConfigSectionAdapter(baseNode),
        Adapter<CommentedConfigurationNode>(baseNode),
        Config {

    override var header: String?
        get() = base.comment.orElse(null)
        set(value) {
            base.setComment(value)
        }
}
