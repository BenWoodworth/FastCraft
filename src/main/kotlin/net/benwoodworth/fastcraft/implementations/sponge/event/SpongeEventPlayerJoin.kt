package net.benwoodworth.fastcraft.implementations.sponge.event

import net.benwoodworth.fastcraft.dependencies.api.player.Player
import net.benwoodworth.fastcraft.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.implementations.sponge.api.player.SpongePlayer
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.network.ClientConnectionEvent

/**
 * Sponge implementation of [EventPlayerJoin].
 */
class SpongeEventPlayerJoin(
        baseEvent: ClientConnectionEvent.Join
) : EventPlayerJoin, Adapter<ClientConnectionEvent.Join>(baseEvent) {

    override val player: Player
        get() = SpongePlayer(base.targetEntity)
}
