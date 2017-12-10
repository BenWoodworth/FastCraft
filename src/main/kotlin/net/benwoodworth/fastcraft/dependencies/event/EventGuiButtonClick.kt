package net.benwoodworth.fastcraft.dependencies.event

import net.benwoodworth.fastcraft.dependencies.gui.Gui
import net.benwoodworth.fastcraft.dependencies.gui.GuiButton
import net.benwoodworth.fastcraft.dependencies.player.Player

/**
 * An event triggered when a [GuiButton] is clicked.
 */
interface EventGuiButtonClick {

    /**
     * The [Gui] in which the button was clicked.
     */
    val gui: Gui

    /**
     * The [GuiButton] that was clicked.
     */
    val button: GuiButton

    /**
     * The [Player] who clicked the button.
     */
    val player: Player?

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
     * The number pressed, or `null` if there was none.
     */
    val number: Int?

    /**
     * Implementation of [EventGuiButtonClick].
     */
    class Impl(
            override val gui: Gui,
            override val button: GuiButton,
            override val player: Player? = null,
            override val primary: Boolean = false,
            override val secondary: Boolean = false,
            override val middle: Boolean = false,
            override val double: Boolean = false,
            override val number: Int? = null
    ) : EventGuiButtonClick
}
