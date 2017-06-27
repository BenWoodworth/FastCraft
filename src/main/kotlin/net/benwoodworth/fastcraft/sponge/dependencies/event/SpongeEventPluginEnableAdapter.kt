package net.benwoodworth.fastcraft.sponge.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginEnable
import net.benwoodworth.fastcraft.core.util.Adapter
import org.spongepowered.api.event.game.state.GamePreInitializationEvent

/**
 * Adapts Sponge plugin enable events.
 */
class SpongeEventPluginEnableAdapter(
        baseEvent: GamePreInitializationEvent
) : EventPluginEnable, Adapter<GamePreInitializationEvent>(baseEvent)
