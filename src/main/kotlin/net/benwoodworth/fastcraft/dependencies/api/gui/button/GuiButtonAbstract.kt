package net.benwoodworth.fastcraft.dependencies.api.gui.button

import net.benwoodworth.fastcraft.dependencies.api.Listener
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiPoint
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElementAbstract
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick

/**
 * An abstract implementation of [GuiButton].
 */
abstract class GuiButtonAbstract(
        region: GuiRegion.Positioned
) : GuiButton, GuiElementAbstract(region) {

    override val clickListener = Listener<GuiEventClick>()

    /**
     * Handles this button's clicks.
     * Runs before [clickListener] handlers are notified.
     *
     * @param event the click event
     */
    protected open fun onClick(event: GuiEventClick) = Unit

    override fun onClick(location: GuiPoint, event: GuiEventClick) {
        onClick(event)
        clickListener.notifyHandlers(event)
    }
}
