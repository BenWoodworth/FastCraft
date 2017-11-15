package net.benwoodworth.fastcraft.dependencies.sponge.event

import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.abstractions.player.Player
import net.benwoodworth.fastcraft.dependencies.sponge.player.SpongePlayer
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
