package net.benwoodworth.fastcraft.api.gui.button

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.element.GuiElementAbstract
import net.benwoodworth.fastcraft.api.gui.event.GuiEventClick

/**
 * An abstract implementation of [GuiButton].
 */
abstract class GuiButtonAbstract(
        x: Int,
        y: Int
) : GuiButton, GuiElementAbstract(x, y, 1, 1) {

    override val clickListener = Listener<GuiEventClick>()

    /**
     * Handles this button's clicks.
     *
     * @param event the click event
     */
    protected abstract fun onClick(event: GuiEventClick)

    override fun onClick(x: Int, y: Int, event: GuiEventClick) {
        onClick(event)
        clickListener.notifyHandlers(event)
    }
}
