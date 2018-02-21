package net.benwoodworth.fastcraft.dependencies.api.gui.button

import net.benwoodworth.fastcraft.dependencies.api.Listener
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElementAbstract
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick

/**
 * An abstract implementation of [GuiButton].
 */
abstract class GuiButtonAbstract(
        region: GuiRegion.Positioned
) : GuiElementAbstract<GuiRegion.Positioned>(region), GuiButton {

    override val clickListener = Listener<GuiEventClick>()

    /**
     * Handles this button's clicks.
     * Runs before [clickListener] handlers are notified.
     *
     * @param event the click event.
     */
    open fun onClick(event: GuiEventClick) = Unit

    override fun click(event: GuiEventClick) {
        click(event)
        clickListener.notifyHandlers(event)
    }
}
