package net.benwoodworth.fastcraft.impl.sponge.event

import net.benwoodworth.fastcraft.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.game.state.GameStoppingEvent

/**
 * Adapts Sponge player join events.
 */
class SpongeEventPluginDisable(
        baseEvent: GameStoppingEvent
) : EventPluginDisable, Adapter<GameStoppingEvent>(baseEvent)
