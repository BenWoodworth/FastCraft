package net.benwoodworth.fastcraft.core.api.gui.events

import net.benwoodworth.fastcraft.core.api.gui.Gui
import net.benwoodworth.fastcraft.core.api.gui.buttons.GuiButton
import net.benwoodworth.fastcraft.core.dependencies.event.Cancellable
import net.benwoodworth.fastcraft.core.dependencies.player.PlayerAdapter

/**
 * An event triggered when a [GuiButton] is clicked.
 */
class GuiButtonClickEvent(
        /** The [Gui] in which the button was clicked. */
        val gui: Gui,

        /** The [GuiButton] that was clicked. */
        val button: GuiButton,

        /** The player who clicked the button. */
        val player: PlayerAdapter
) : Cancellable {

    override var cancelled = false
}
