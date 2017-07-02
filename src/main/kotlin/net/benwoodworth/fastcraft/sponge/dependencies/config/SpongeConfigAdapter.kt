package net.benwoodworth.fastcraft.sponge.dependencies.config

import net.benwoodworth.fastcraft.core.dependencies.config.Config
import net.benwoodworth.fastcraft.core.dependencies.config.ConfigSection
import net.benwoodworth.fastcraft.core.util.Adapter
import ninja.leaping.configurate.commented.CommentedConfigurationNode
import java.nio.file.Path

/**
 * Sponge implementation of [Config].
 */
class SpongeConfigAdapter(
        baseNode: CommentedConfigurationNode
) : Config, ConfigSection by SpongeConfigSectionAdapter(baseNode), Adapter<CommentedConfigurationNode>(baseNode) {

    override var header: String?
        get() = base.comment.orElse(null)
        set(value) {
            base.setComment(value)
        }

    override fun load(path: Path) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(path: Path) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
