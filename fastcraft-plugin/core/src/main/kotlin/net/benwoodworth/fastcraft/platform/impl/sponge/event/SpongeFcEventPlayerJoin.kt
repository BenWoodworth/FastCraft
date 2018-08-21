package net.benwoodworth.fastcraft.implementations.sponge.event

import net.benwoodworth.fastcraft.dependencies.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.event.FcEventPlayerJoin
import net.benwoodworth.fastcraft.implementations.sponge.player.SpongeFcPlayer
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.network.ClientConnectionEvent

/**
 * Sponge implementation of [FcEventPlayerJoin].
 */
class SpongeFcEventPlayerJoin(
        override val base: ClientConnectionEvent.Join
) : FcEventPlayerJoin, Adapter<ClientConnectionEvent.Join>() {

    override val player: FcPlayer
        get() = SpongeFcPlayer(base.targetEntity)
}
