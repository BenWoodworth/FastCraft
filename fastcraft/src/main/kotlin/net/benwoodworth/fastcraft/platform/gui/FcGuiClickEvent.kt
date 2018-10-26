package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.event.FcEvent

/**
 * An event triggered when an [FcGui] is clicked.
 */
interface FcGuiClickEvent : FcEvent {

    /**
     * The [FcGui] in which the event occurred.
     */
    val gui: FcGui

    /**
     * The [FcPlayer] who clicked.
     */
    val player: FcPlayer?

    /**
     * Whether the click was with the primary mouse button.
     */
    val primary: Boolean

    /**
     * Whether the click was with the primary mouse button.
     */
    val secondary: Boolean

    /**
     * Whether the click was with the middle mouse button.
     */
    val middle: Boolean

    /**
     * Whether the click was a double click.
     */
    val double: Boolean

    /**
     * The number pressed, from 1..9, or `null` if there was none.
     */
    val number: Int?

    /**
     * Whether shift was held while clicking the button.
     */
    val shift: Boolean
}
