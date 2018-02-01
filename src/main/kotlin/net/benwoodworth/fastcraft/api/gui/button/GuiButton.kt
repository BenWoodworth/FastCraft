package net.benwoodworth.fastcraft.api.gui.button

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.element.GuiElement
import net.benwoodworth.fastcraft.api.gui.event.EventGuiButtonClick
import net.benwoodworth.fastcraft.dependencies.item.Item

/**
 * A button in a GUI.
 */
interface GuiButton : GuiElement {

    /**
     * A listener for button clicks.
     */
    val clickListener: Listener<EventGuiButtonClick>

    /**
     * The item representing this button.
     */
    val item: Item?
}
