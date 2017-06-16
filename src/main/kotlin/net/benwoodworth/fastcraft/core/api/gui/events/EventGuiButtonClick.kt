package net.benwoodworth.fastcraft.core.api.gui.events

import net.benwoodworth.fastcraft.core.api.gui.Gui
import net.benwoodworth.fastcraft.core.api.gui.buttons.GuiButton
import net.benwoodworth.fastcraft.core.dependencies.event.FcCancellable
import net.benwoodworth.fastcraft.core.dependencies.event.FcEvent
import net.benwoodworth.fastcraft.core.dependencies.player.PlayerAdapter

/**
 * An event triggered when a [GuiButton] is clicked.
 */
class EventGuiButtonClick(
        /** The [Gui] in which the button was clicked. */
        val gui: Gui,

        /** The [GuiButton] that was clicked. */
        val button: GuiButton,

        /** The [FcPlayer] who clicked the button. */
        val player: PlayerAdapter
) : FcEvent, FcCancellable {

    override var cancelled = false
}
