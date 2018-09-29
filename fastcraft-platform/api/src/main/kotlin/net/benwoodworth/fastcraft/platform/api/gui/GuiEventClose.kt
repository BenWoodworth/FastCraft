package net.benwoodworth.fastcraft.platform.api.gui

import net.benwoodworth.fastcraft.platform.api.player.FcPlayer

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
