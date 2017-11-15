package net.benwoodworth.fastcraft.dependencies.abstractions.event

import net.benwoodworth.fastcraft.dependencies.abstractions.player.Player

/**
 * An event for players joining the server.
 */
interface EventPlayerJoin {

    /**
     * The player who joined.
     */
    val player: Player
}
