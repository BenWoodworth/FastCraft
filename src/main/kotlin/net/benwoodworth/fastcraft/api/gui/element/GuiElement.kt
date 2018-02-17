package net.benwoodworth.fastcraft.api.gui.element

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.api.gui.region.GuiRegionRectangle
import net.benwoodworth.fastcraft.dependencies.item.Item

/**
 * An object that can be added to a [Gui].
 */
interface GuiElement : GuiRegionRectangle {

    /**
     * A listener for element changes.
     */
    val changeListener: Listener<GuiEventLayoutChange>

    /**
     * Handles GUI clicks.
     *
     * @param x the x-position that was clicked, relative to this element
     * @param y the y-position that was clicked, relative to this element
     * @param event the click event
     */
    fun onClick(x: Int, y: Int, event: GuiEventClick)

    /**
     * Get an item from within this element.
     *
     * @param x the x-position of the item, relative to this element
     * @param y the y-position of the item, relative to this element
     * @return the item at the specified position, or `null` if there is none
     */
    fun getItem(x: Int, y: Int): Item?

    /**
     * A [GuiElement] that can be moved and resized.
     */
    interface Mutable : GuiElement {
        override var x: Int
        override var y: Int
        override var width: Int
        override var height: Int
    }
}