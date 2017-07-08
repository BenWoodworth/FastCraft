package net.benwoodworth.fastcraft.impl.sponge

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
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

    @Listener
    @Suppress("UNUSED_PARAMETER")
    fun onPreInit(event: GamePreInitializationEvent) {
        instance = DaggerSpongeFastCraftComponent.builder()
                .build().getFastCraft()
    }
}
