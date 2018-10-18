package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.player.FcPlayer

/**
 * An event for closed [FcGui]'s.
 */
interface GuiEventClose {

    /**
     * The [FcGui] in which the event occurred.
     */
    val gui: FcGui

    /**
     * The [FcPlayer] who closed the GUI.
     */
    val player: FcPlayer?
}
