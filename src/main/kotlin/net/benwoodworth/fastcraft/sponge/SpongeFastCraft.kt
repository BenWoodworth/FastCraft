package net.benwoodworth.fastcraft.sponge

import net.benwoodworth.fastcraft.core.FastCraft
import net.benwoodworth.fastcraft.core.FastCraftImplementation
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GamePreInitializationEvent

/**
 * Sponge implementation of FastCraft.
 */
class SpongeFastCraft : FastCraftImplementation {

    override lateinit var instance: FastCraft
        private set

    @Listener
    @Suppress("UNUSED_PARAMETER")
    fun onPreInit(unused: GamePreInitializationEvent) {
        instance = DaggerSpongeFastCraftComponent.builder()
                .spongeFastCraftModule(SpongeFastCraftModule(this))
                .build()
                .getFastCraft()
    }
}
