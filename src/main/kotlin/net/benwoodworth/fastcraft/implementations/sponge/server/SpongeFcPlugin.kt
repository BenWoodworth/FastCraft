package net.benwoodworth.fastcraft.implementations.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.FcLogger
import net.benwoodworth.fastcraft.dependencies.server.FcPlugin
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.Sponge
import org.spongepowered.api.plugin.PluginContainer
import java.nio.file.Path

/**
 * An adapter for Sponge plugins.
 */
class SpongeFcPlugin(
        override val base: PluginContainer
) : FcPlugin, Adapter<PluginContainer>() {

    override val logger: FcLogger
        get() = SpongeFcLogger(base.logger)

    override val pluginDirectory: Path
        get() = Sponge.getConfigManager().getPluginConfig(base.instance.get()).directory

    override val configFileName: String
        get() = Sponge.getConfigManager().getPluginConfig(base.instance.get()).configPath.fileName.toString()
}
