package net.benwoodworth.fastcraft.impl.sponge.event

import net.benwoodworth.fastcraft.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.impl.sponge.player.SpongePlayer
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.event.network.ClientConnectionEvent

/**
 * Adapts Sponge player join events.
 */
class SpongeEventPlayerJoin(
        private val baseEvent: ClientConnectionEvent.Join
) : EventPlayerJoin, Adapter<ClientConnectionEvent.Join>(baseEvent) {

    override val player: Player
        get() = SpongePlayer(baseEvent.targetEntity)
}
