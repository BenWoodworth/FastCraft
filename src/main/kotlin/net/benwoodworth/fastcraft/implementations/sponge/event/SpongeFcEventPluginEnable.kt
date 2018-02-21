package net.benwoodworth.fastcraft.implementations.sponge.event

import net.benwoodworth.fastcraft.dependencies.event.FcEventPluginEnable
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.game.state.GameInitializationEvent

/**
 * Sponge implementation of [FcEventPluginEnable].
 */
class SpongeFcEventPluginEnable(
        baseEvent: GameInitializationEvent
) : FcEventPluginEnable, Adapter<GameInitializationEvent>(baseEvent)
