package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.util.Extensible

/**
 * An event for closed [FcGui]'s.
 */
interface FcGuiCloseEvent : Extensible, FcEvent {

    /**
     * The [FcGui] in which the event occurred.
     */
    val gui: FcGui

    /**
     * The [FcPlayer] who closed the GUI.
     */
    val player: FcPlayer?
}
