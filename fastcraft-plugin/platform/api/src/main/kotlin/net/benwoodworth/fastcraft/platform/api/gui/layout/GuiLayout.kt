package net.benwoodworth.fastcraft.platform.api.gui.layout

import net.benwoodworth.fastcraft.platform.api.gui.FcGuiPosition
import net.benwoodworth.fastcraft.platform.api.gui.element.GuiElement

/**
 * The button layout of a GUI.
 */
interface GuiLayout : GuiElement {

    /**
     * Add a [GuiElement] to this layout.
     *
     * Elements added later will take precedence over layouts added earlier.
     *
     * @param element the [GuiElement] to add.
     */
    fun addElement(element: GuiElement)

    /**
     * Get the child layout at a the specified position.
     *
     * If layouts are overlapping at the specified point,
     * the last layout added will be returned.
     *
     * @param element the [GuiElement] to remove.
     */
    fun removeElement(element: GuiElement)

    /**
     * Get the layout element at the specified position in the layout.
     *
     * @param position the position of the button.
     * @return the element at the specified position, or `null` if there is none.
     */
    fun getElement(position: FcGuiPosition): GuiElement?
}
