package net.benwoodworth.fastcraft.impl.sponge

import com.google.inject.Inject
import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import net.benwoodworth.fastcraft.impl.sponge.config.SpongeModuleConfig
import net.benwoodworth.fastcraft.impl.sponge.event.SpongeModuleEvent
import net.benwoodworth.fastcraft.impl.sponge.gui.SpongeModuleGui
import net.benwoodworth.fastcraft.impl.sponge.item.SpongeModuleItem
import net.benwoodworth.fastcraft.impl.sponge.player.SpongeModulePlayer
import net.benwoodworth.fastcraft.impl.sponge.server.SpongeModuleServer
import net.benwoodworth.fastcraft.impl.sponge.text.SpongeModuleText
import org.bstats.sponge.Metrics as SpongeBstatsMetrics
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GamePreInitializationEvent
import org.spongepowered.api.plugin.Plugin

/**
 * Sponge implementation of FastCraft.
 */
@Plugin(id = "fastcraft")
class SpongeFastCraft : FastCraftImplementation {

    override lateinit var instance: FastCraft
        private set

    @Inject
    private lateinit var metrics: SpongeBstatsMetrics

    @Listener
    @Suppress("UNUSED_PARAMETER", "DEPRECATION") // TODO Don't suppress deprecation
    fun onPreInit(event: GamePreInitializationEvent) {
        val fastCraftBuilder = DaggerSpongeDependenciesComponent.builder()
                .spongeModuleConfig(SpongeModuleConfig())
                .spongeModuleEvent(SpongeModuleEvent(this))
                .spongeModuleGui(SpongeModuleGui(this))
                .spongeModuleItem(SpongeModuleItem(this))
                .spongeModulePlayer(SpongeModulePlayer())
                .spongeModuleServer(SpongeModuleServer(this))
                .spongeModuleText(SpongeModuleText())

        instance = fastCraftBuilder.build().getFastCraft()
    }
}
