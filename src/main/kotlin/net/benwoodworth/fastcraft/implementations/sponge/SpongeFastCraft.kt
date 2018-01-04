package net.benwoodworth.fastcraft.implementations.sponge

import com.google.inject.Inject
import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GamePreInitializationEvent
import org.spongepowered.api.plugin.Plugin
import org.bstats.sponge.Metrics as SpongeBstatsMetrics

/**
 * Sponge implementation of FastCraft.
 */
@Plugin(id = "fastcraft")
@Suppress("UNUSED")
class SpongeFastCraft : FastCraftImplementation {

    override lateinit var instance: FastCraft
        private set

    @Inject
    @Suppress("UNUSED")
    private lateinit var metrics: SpongeBstatsMetrics

    @Listener
    @Suppress("UNUSED", "UNUSED_PARAMETER", "DEPRECATION") // TODO Don't suppress deprecation
    fun onPreInit(event: GamePreInitializationEvent) {
        val fastCraftBuilder = DaggerSpongeFastCraftComponent.builder()
                .spongeFastCraftModule(SpongeFastCraftModule(this))

        instance = fastCraftBuilder.build().getFastCraft()
    }
}
