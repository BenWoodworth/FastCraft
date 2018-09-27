package net.benwoodworth.fastcraft.platform.api.gui.button

import net.benwoodworth.fastcraft.platform.api.event.FcListener
import net.benwoodworth.fastcraft.platform.api.gui.element.GuiElement
import net.benwoodworth.fastcraft.platform.api.gui.event.GuiEventClick

/**
 * A button in a GUI.
 */
interface GuiButton : GuiElement {

    /**
     * A listener for button clicks.
     */
    val clickListener: FcListener<GuiEventClick>
}
