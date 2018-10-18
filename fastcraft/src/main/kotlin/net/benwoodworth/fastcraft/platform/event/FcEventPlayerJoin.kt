package net.benwoodworth.fastcraft.platform.event

import net.benwoodworth.fastcraft.platform.player.FcPlayer

/**
 * An event for players joining the server.
 */
interface FcEventPlayerJoin {

    /**
     * The player who joined.
     */
    val player: FcPlayer
}
