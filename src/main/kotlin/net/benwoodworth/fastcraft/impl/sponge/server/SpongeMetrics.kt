package net.benwoodworth.fastcraft.impl.sponge.server

import net.benwoodworth.fastcraft.dependencies.server.Metrics
import net.benwoodworth.fastcraft.dependencies.server.Plugin
import org.spongepowered.api.Sponge

class SpongeMetrics : Metrics {

    override fun start(plugin: Plugin) {
        (plugin as SpongePlugin).base.let {
            SpongeBstatsLite(
                    it,
                    it.logger,
                    Sponge.getConfigManager().getSharedConfig(it.instance).directory
            )
        }
    }
}