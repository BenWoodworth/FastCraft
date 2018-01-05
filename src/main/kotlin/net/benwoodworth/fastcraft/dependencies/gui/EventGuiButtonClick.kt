package net.benwoodworth.fastcraft.dependencies.gui

import net.benwoodworth.fastcraft.dependencies.player.Player

/**
 * An event triggered when a [GuiButton] is clicked.
 */
class EventGuiButtonClick(

        /**
     * The [Gui] in which the button was clicked.
     */
    val gui: Gui,

        /**
     * The [GuiButton] that was clicked.
     */
    val button: GuiButton,

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
