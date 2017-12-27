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
    val player: Player? = null,

    /**
     * Whether the click was with the primary mouse button.
     */
    val primary: Boolean = false,

    /**
     * Whether the click was with the primary mouse button.
     */
    val secondary: Boolean = false,

    /**
     * Whether the click was with the middle mouse button.
     */
    val middle: Boolean = false,

    /**
     * Whether the click was a double click.
     */
    val double: Boolean = false,

    /**
     * The number pressed, or `null` if there was none.
     */
    val number: Int? = null
)
