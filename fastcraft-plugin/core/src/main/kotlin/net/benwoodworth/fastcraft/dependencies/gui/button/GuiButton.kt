package net.benwoodworth.fastcraft.dependencies.gui.button

import net.benwoodworth.fastcraft.dependencies.event.FcListener
import net.benwoodworth.fastcraft.dependencies.gui.element.GuiElement
import net.benwoodworth.fastcraft.dependencies.gui.event.GuiEventClick

/**
 * A button in a GUI.
 */
interface GuiButton : GuiElement {

    /**
     * A listener for button clicks.
     */
    val clickListener: FcListener<GuiEventClick>
}
