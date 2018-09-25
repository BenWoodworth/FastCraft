package net.benwoodworth.fastcraft.platform.sponge.event

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.event.FcEventPlayerJoin
import net.benwoodworth.fastcraft.platform.sponge.player.SpongeFcPlayer
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
