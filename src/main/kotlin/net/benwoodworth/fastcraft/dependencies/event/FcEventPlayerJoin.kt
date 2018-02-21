package net.benwoodworth.fastcraft.dependencies.event

import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer

/**
 * An event for players joining the server.
 */
interface FcEventPlayerJoin {

    /**
     * The player who joined.
     */
    val player: FcPlayer
}
