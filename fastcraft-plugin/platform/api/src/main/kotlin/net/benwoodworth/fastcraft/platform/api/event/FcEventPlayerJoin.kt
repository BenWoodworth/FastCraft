package net.benwoodworth.fastcraft.platform.api.event

import net.benwoodworth.fastcraft.platform.api.player.FcPlayer

/**
 * An event for players joining the server.
 */
interface FcEventPlayerJoin {

    /**
     * The player who joined.
     */
    val player: FcPlayer
}
