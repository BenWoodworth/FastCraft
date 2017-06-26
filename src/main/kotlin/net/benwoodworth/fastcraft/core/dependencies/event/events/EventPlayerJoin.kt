package net.benwoodworth.fastcraft.core.dependencies.event.events

import net.benwoodworth.fastcraft.core.dependencies.player.Player

/**
 * An event for players joining the server.
 */
interface EventPlayerJoin {
    /** The player who joined. */
    val player: Player
}
