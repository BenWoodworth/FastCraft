package net.benwoodworth.fastcraft.sponge.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.core.util.Adapter
import org.spongepowered.api.event.game.state.GamePreInitializationEvent
import org.spongepowered.api.event.game.state.GameStoppingEvent

/**
 * Adapts Sponge player join events.
 */
class SpongeEventPluginDisableAdapter(
        baseEvent: GameStoppingEvent
) : EventPluginDisable, Adapter<GameStoppingEvent>(baseEvent)
