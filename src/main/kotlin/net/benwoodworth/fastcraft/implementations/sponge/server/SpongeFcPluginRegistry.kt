package net.benwoodworth.fastcraft.implementations.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.FcPlugin
import net.benwoodworth.fastcraft.dependencies.server.FcPluginRegistry
import org.spongepowered.api.Sponge

/**
 * Sponge implementation of [FcPluginRegistry].
 */
class SpongeFcPluginRegistry : FcPluginRegistry {

    override fun getPlugin(name: String): FcPlugin? {
        return Sponge.getPluginManager().getPlugin(name)
                .map(::SpongeFcPlugin)
                .orElse(null)
    }
}