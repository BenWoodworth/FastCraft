package net.benwoodworth.fastcraft.dependencies.api.gui.element

import net.benwoodworth.fastcraft.dependencies.api.Listener
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.dependencies.api.gui.region.GuiPoint
import net.benwoodworth.fastcraft.dependencies.api.gui.region.GuiRegion
import net.benwoodworth.fastcraft.dependencies.api.item.Item

/**
 * An object that can be added to a GUI.
 */
interface GuiElement {

    /**
     * A listener for element changes.
     */
    val changeListener: Listener<GuiEventLayoutChange>

    /**
     * The region this element occupies within the containing layout.
     */
    val region: GuiRegion

    /**
     * Handles GUI clicks.
     *
     * @param point the location that was clicked, relative to this element
     * @param event the click event
     */
    fun onClick(point: GuiPoint, event: GuiEventClick)

    /**
     * Get an item from within this element.
     *
     * @param point the location of the item, relative to this element
     * @return the item at the specified position, or `null` if there is none
     */
    fun getItem(point: GuiPoint): Item?

    /**
     * A [GuiElement] that can be moved and resized.
     */
    interface Mutable : GuiElement {

        override val region: GuiRegion
    }
}