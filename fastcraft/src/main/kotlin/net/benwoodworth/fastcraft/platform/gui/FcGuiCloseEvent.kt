package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.event.FcEvent

/**
 * An event for closed [FcGui]'s.
 */
interface FcGuiCloseEvent : FcEvent {

    /**
     * The [FcGui] in which the event occurred.
     */
    val gui: FcGui

    /**
     * The [FcPlayer] who closed the GUI.
     */
    val player: FcPlayer?
}
