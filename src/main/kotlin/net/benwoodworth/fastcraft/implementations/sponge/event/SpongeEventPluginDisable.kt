package net.benwoodworth.fastcraft.implementations.sponge.event

import net.benwoodworth.fastcraft.dependencies.event.EventPluginDisable
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.game.state.GameStoppingEvent

/**
 * Sponge implementation of [EventPluginDisable].
 */
class SpongeEventPluginDisable(
        baseEvent: GameStoppingEvent
) : EventPluginDisable, Adapter<GameStoppingEvent>(baseEvent)
