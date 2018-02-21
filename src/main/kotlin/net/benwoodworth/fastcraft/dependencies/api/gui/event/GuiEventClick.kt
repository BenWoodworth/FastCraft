package net.benwoodworth.fastcraft.dependencies.api.gui.event

import net.benwoodworth.fastcraft.dependencies.api.gui.Gui
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiLocation
import net.benwoodworth.fastcraft.dependencies.api.gui.button.GuiButton
import net.benwoodworth.fastcraft.dependencies.api.player.Player

/**
 * An event triggered when a [GuiButton] is clicked.
 *
 * @property location The location that was clicked.
 * @property gui The [Gui] in which the event occurred.
 * @property player The [Player] who clicked the button.
 * @property primary Whether the click was with the primary mouse button.
 * @property secondary Whether the click was with the primary mouse button.
 * @property middle Whether the click was with the middle mouse button.
 * @property double Whether the click was a double click.
 * @property number The number pressed, from 1..9, or `null` if there was none.
 * @property shift Whether shift was held while clicking the button.
 */
data class GuiEventClick(
        val location: GuiLocation,
        val gui: Gui,
        val player: Player?,
        val primary: Boolean,
        val secondary: Boolean,
        val middle: Boolean,
        val double: Boolean,
        val number: Int?,
        val shift: Boolean
)
