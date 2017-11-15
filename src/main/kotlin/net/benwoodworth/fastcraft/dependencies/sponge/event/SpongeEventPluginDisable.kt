package net.benwoodworth.fastcraft.dependencies.sponge.event

import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventPluginDisable
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.game.state.GameStoppingEvent

/**
 * Sponge implementation of [EventPluginDisable].
 */
class SpongeEventPluginDisable(
        baseEvent: GameStoppingEvent
) : EventPluginDisable, Adapter<GameStoppingEvent>(baseEvent)
