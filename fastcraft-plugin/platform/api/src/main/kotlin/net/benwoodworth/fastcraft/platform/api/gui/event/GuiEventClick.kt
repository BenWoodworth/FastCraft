package net.benwoodworth.fastcraft.platform.api.gui.event

import net.benwoodworth.fastcraft.platform.api.gui.FcGui
import net.benwoodworth.fastcraft.platform.api.gui.FcGuiPosition
import net.benwoodworth.fastcraft.platform.api.gui.button.GuiButton
import net.benwoodworth.fastcraft.platform.api.player.FcPlayer

/**
 * An event triggered when a [GuiButton] is clicked.
 *
 * @property position The position that was clicked.
 * @property gui The [FcGui] in which the event occurred.
 * @property player The [FcPlayer] who clicked the button.
 * @property primary Whether the click was with the primary mouse button.
 * @property secondary Whether the click was with the primary mouse button.
 * @property middle Whether the click was with the middle mouse button.
 * @property double Whether the click was a double click.
 * @property number The number pressed, from 1..9, or `null` if there was none.
 * @property shift Whether shift was held while clicking the button.
 */
data class GuiEventClick(
        val position: FcGuiPosition,
        val gui: FcGui,
        val player: FcPlayer?,
        val primary: Boolean,
        val secondary: Boolean,
        val middle: Boolean,
        val double: Boolean,
        val number: Int?,
        val shift: Boolean
)
