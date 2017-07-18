package net.benwoodworth.fastcraft.impl.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.Logger
import net.benwoodworth.fastcraft.dependencies.server.Plugin
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.Sponge
import org.spongepowered.api.plugin.PluginContainer
import java.nio.file.Path

/**
 * An adapter for Sponge plugins.
 */
class SpongePlugin(
        plugin: PluginContainer
) : Plugin, Adapter<PluginContainer>(plugin) {

    override val logger: Logger
        get() = SpongeLogger(base.logger)

    override val pluginDirectory: Path
        get() = Sponge.getConfigManager().getPluginConfig(base.instance.get()).directory

    override val configFile: Path
        get() = Sponge.getConfigManager().getPluginConfig(base.instance.get()).configPath

    /**
     * Implementation of [Plugin.Provider].
     */
    class Provider : Plugin.Provider {

        override fun getPlugin(name: String): Plugin? {
            return Sponge.getPluginManager().getPlugin(name)
                    .map(::SpongePlugin)
                    .orElse(null)
        }
    }
}
