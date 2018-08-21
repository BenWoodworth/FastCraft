package net.benwoodworth.fastcraft.platform.gui.button

import net.benwoodworth.fastcraft.platform.event.FcListener
import net.benwoodworth.fastcraft.platform.gui.element.GuiElement
import net.benwoodworth.fastcraft.platform.gui.event.GuiEventClick

/**
 * A button in a GUI.
 */
interface GuiButton : GuiElement {

    /**
     * A listener for button clicks.
     */
    val clickListener: FcListener<GuiEventClick>
}
