package net.benwoodworth.fastcraft.impl.sponge.event

import net.benwoodworth.fastcraft.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.game.state.GameInitializationEvent

/**
 * Sponge implementation of [EventPluginEnable].
 */
class SpongeEventPluginEnable(
        baseEvent: GameInitializationEvent
) : EventPluginEnable, Adapter<GameInitializationEvent>(baseEvent)
