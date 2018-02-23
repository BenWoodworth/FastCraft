package net.benwoodworth.fastcraft.dependencies.api.gui.button

import net.benwoodworth.fastcraft.dependencies.api.event.FcListener
import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElement
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick

/**
 * A button in a GUI.
 */
interface GuiButton : GuiElement {

    /**
     * A listener for button clicks.
     */
    val clickListener: FcListener<GuiEventClick>
}
