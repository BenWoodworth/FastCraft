package net.benwoodworth.fastcraft.core.dependencies.event.events

import net.benwoodworth.fastcraft.core.dependencies.event.FcEvent
import net.benwoodworth.fastcraft.core.dependencies.player.PlayerAdapter

/**
 * An event for players joining the server.
 */
interface FcEventPlayerJoin : FcEvent {

    /** The player who joined. */
    val player: PlayerAdapter
}
