package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.platform.event.FcEvent

/**
 * An event for players joining the server.
 */
interface FcPlayerJoinEvent : FcEvent {

    /**
     * The player who joined.
     */
    val player: FcPlayer
}
