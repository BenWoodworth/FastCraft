package net.benwoodworth.fastcraft.platform.impl.sponge.event

import net.benwoodworth.fastcraft.platform.event.FcEventPluginEnable
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.game.state.GameInitializationEvent

/**
 * Sponge implementation of [FcEventPluginEnable].
 */
class SpongeFcEventPluginEnable(
        override val base: GameInitializationEvent
) : FcEventPluginEnable, Adapter<GameInitializationEvent>()
