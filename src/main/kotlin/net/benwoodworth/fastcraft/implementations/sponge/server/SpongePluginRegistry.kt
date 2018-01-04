package net.benwoodworth.fastcraft.implementations.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.Plugin
import net.benwoodworth.fastcraft.dependencies.server.PluginRegistry
import org.spongepowered.api.Sponge

/**
 * Sponge implementation of [PluginRegistry].
 */
class SpongePluginRegistry : PluginRegistry {

    override fun getPlugin(name: String): Plugin? {
        return Sponge.getPluginManager().getPlugin(name)
                .map(::SpongePlugin)
                .orElse(null)
    }
}