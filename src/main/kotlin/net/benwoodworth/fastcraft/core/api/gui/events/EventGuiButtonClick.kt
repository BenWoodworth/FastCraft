package net.benwoodworth.fastcraft.core.api.gui.events

import net.benwoodworth.fastcraft.core.api.gui.Gui
import net.benwoodworth.fastcraft.core.api.gui.buttons.GuiButton
import net.benwoodworth.fastcraft.core.dependencies.event.FcCancellable
import net.benwoodworth.fastcraft.core.dependencies.event.FcEvent
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem
import net.benwoodworth.fastcraft.core.dependencies.player.FcPlayer

/**
 * An event triggered when a [GuiButton] is clicked.
 *
 * @param TItem the item type
 */
class EventGuiButtonClick<TItem : FcItem<*>>(
        /** The [Gui] in which the button was clicked. */
        val gui: Gui<TItem>,

        /** The [GuiButton] that was clicked. */
        val button: GuiButton<TItem>,

        /** The [FcPlayer] who clicked the button. */
        val player: FcPlayer<TItem>
) : FcEvent, FcCancellable {

    override var cancelled = false
}
