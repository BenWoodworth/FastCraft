package net.benwoodworth.fastcraft.sponge.dependencies.event

import net.benwoodworth.fastcraft.core.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.player.Player
import net.benwoodworth.fastcraft.core.util.Adapter
import org.spongepowered.api.event.network.ClientConnectionEvent

/**
 * Adapts Sponge player join events.
 */
class SpongeEventPlayerJoin(
        baseEvent: ClientConnectionEvent.Join
) : EventPlayerJoin, Adapter<ClientConnectionEvent.Join>(baseEvent) {

    override val player: Player
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}
