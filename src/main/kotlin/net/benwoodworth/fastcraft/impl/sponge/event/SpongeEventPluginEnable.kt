package net.benwoodworth.fastcraft.impl.sponge.event

import net.benwoodworth.fastcraft.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.game.state.GamePreInitializationEvent

/**
 * Adapts Sponge plugin enable events.
 */
class SpongeEventPluginEnable(
        baseEvent: GamePreInitializationEvent
) : EventPluginEnable, Adapter<GamePreInitializationEvent>(baseEvent)
