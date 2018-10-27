package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.util.Extensible

/**
 * An event for players joining the server.
 */
interface FcPlayerJoinEvent : Extensible, FcEvent {

    /**
     * The player who joined.
     */
    val player: FcPlayer
}
