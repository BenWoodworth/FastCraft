package net.benwoodworth.fastcraft.platform.impl.sponge.server

import net.benwoodworth.fastcraft.platform.server.FcPlugin
import net.benwoodworth.fastcraft.platform.server.FcPluginRegistry
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