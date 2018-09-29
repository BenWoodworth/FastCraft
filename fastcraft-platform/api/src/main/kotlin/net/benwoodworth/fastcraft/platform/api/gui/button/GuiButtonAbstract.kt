package net.benwoodworth.fastcraft.platform.api.gui.button

import net.benwoodworth.fastcraft.platform.api.event.FcListener
import net.benwoodworth.fastcraft.platform.api.gui.FcGuiRegion
import net.benwoodworth.fastcraft.platform.api.gui.element.GuiElementAbstract
import net.benwoodworth.fastcraft.platform.api.gui.event.GuiEventClick

/**
 * An abstract implementation of [GuiButton].
 */
abstract class GuiButtonAbstract(
    region: FcGuiRegion.Positioned
) : GuiElementAbstract<FcGuiRegion.Positioned>(region), GuiButton {

    override val clickListener = FcListener<GuiEventClick>()

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
