package net.benwoodworth.fastcraft.dependencies.sponge.event

import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventPluginEnable
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.game.state.GameInitializationEvent

/**
 * Sponge implementation of [EventPluginEnable].
 */
class SpongeEventPluginEnable(
        baseEvent: GameInitializationEvent
) : EventPluginEnable, Adapter<GameInitializationEvent>(baseEvent)
