package net.benwoodworth.fastcraft.core.dependencies.gui.layouts

import net.benwoodworth.fastcraft.core.dependencies.gui.buttons.GuiButton
import net.benwoodworth.fastcraft.core.dependencies.gui.events.EventGuiLayoutChange
import net.benwoodworth.fastcraft.core.dependencies.event.EventListener

/**
 * The layout of a GUI.
 * Layouts are composite, so they're made up of other layouts.
 */
open class GuiLayout(
        /** The width of this [GuiLayout]. */
        open val width: Int,

        /** The height of this [GuiLayout]. */
        open val height: Int
) {

    /** A listener for layout changes. */
    val layoutChangeListener = EventListener<EventGuiLayoutChange>()

    /** The layouts that compose this layout. */
    private val childLayouts = ArrayList<LayoutPosition>()

    /**
     * Add a child layout or button at the specified location.
     *
     * @param layout the layout to add
     */
    fun addLayout(layout: GuiLayout, x: Int, y: Int) {
        childLayouts += LayoutPosition(layout, x, y)
        layout.layoutChangeListener.addHandler(layoutChangeListener::notifyHandlers)
    }

    /**
     * Remove a child layout or button.
     */
    fun removeLayout(layout: GuiLayout) {
        val layoutPosition = childLayouts.find { it.layout === layout } ?: return
        childLayouts.remove(layoutPosition)
        layout.layoutChangeListener.removeHandler(layoutChangeListener::notifyHandlers)
    }

    /**
     * Get a button at the specified position.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @return the button ath the specified position, or null if there is none
     */
    fun getButton(x: Int, y: Int): GuiButton? {
        val layout = childLayoutAtPoint(x, y).layout
        return if (layout is GuiButton) layout else null
    }

    /**
     * Get the layout at a point.
     *
     * @param x the x-coordinate within this layout
     * @param y the y-coordinate within this layout
     * @return the layout at the specified point, and the coordinates
     *         within that layout that these coordinates point to
     */
    private fun childLayoutAtPoint(x: Int, y: Int): LayoutPosition {
        for (layoutPosition in childLayouts) {
            val xRange = (layoutPosition.x)..(layoutPosition.x + layoutPosition.layout.width)
            val yRange = (layoutPosition.y)..(layoutPosition.y + layoutPosition.layout.height)

            if (x in xRange && y in yRange) {
                val newX = x - layoutPosition.layout.width
                val newY = y - layoutPosition.layout.height
                return layoutPosition.layout.childLayoutAtPoint(newX, newY)
            }
        }

        // No child layout at point
        return LayoutPosition(this, x, y)
    }

    /**
     * Contains a layout, and a coordinate pair.
     */
    private class LayoutPosition(
            /** The layout. */
            val layout: GuiLayout,

            /** The x-coordinate */
            val x: Int,

            /** The y-coordinate */
            val y: Int
    )
}
