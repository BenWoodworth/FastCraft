package net.benwoodworth.fastcraft.dependencies.api.gui.event

import net.benwoodworth.fastcraft.dependencies.api.gui.Gui
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiLocation
import net.benwoodworth.fastcraft.dependencies.api.gui.button.GuiButton
import net.benwoodworth.fastcraft.dependencies.api.player.Player

/**
 * An event triggered when a [GuiButton] is clicked.
 */
data class GuiEventClick(
        /**
         * The location that was clicked.
         */
        val location: GuiLocation,

        /**
         * The [Gui] in which the event occurred.
         */
        val gui: Gui,

        /**
         * The [Player] who clicked the button.
         */
        val player: Player?,

        /**
         * Whether the click was with the primary mouse button.
         */
        val primary: Boolean,

        /**
         * Whether the click was with the primary mouse button.
         */
        val secondary: Boolean,

        /**
         * Whether the click was with the middle mouse button.
         */
        val middle: Boolean,

        /**
         * Whether the click was a double click.
         */
        val double: Boolean,

        /**
         * The number pressed, from 1..9, or `null` if there was none.
         */
        val number: Int?,

        /**
         * Whether shift was held while clicking the button.
         */
        val shift: Boolean
)
