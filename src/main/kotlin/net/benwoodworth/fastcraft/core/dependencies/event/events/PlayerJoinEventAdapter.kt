package net.benwoodworth.fastcraft.core.dependencies.event.events

import net.benwoodworth.fastcraft.core.dependencies.Adapter
import net.benwoodworth.fastcraft.core.dependencies.player.PlayerAdapter

/**
 * An event for players joining the server.
 */
abstract class PlayerJoinEventAdapter(baseEvent: Any) : Adapter(baseEvent) {

    /** The player who joined. */
    abstract val player: PlayerAdapter
}
