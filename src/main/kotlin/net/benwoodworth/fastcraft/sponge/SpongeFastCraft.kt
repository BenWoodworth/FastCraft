package net.benwoodworth.fastcraft.sponge

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GamePreInitializationEvent

/**
 * Sponge implementation of FastCraft.
 */
class SpongeFastCraft : FastCraftImplementation {

    lateinit override var instance: FastCraft
        private set

    @Listener
    fun onPreInit(event: GamePreInitializationEvent) {
        instance = DaggerSpongeFastCraftComponent.builder()
                .spongeFastCraftModule(SpongeFastCraftModule(this))
                .build()
                .getFastCraft()
    }
}
