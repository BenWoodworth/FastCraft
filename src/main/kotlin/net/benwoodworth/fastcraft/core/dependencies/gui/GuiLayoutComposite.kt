package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.gui.events.EventGuiLayoutChange
import net.benwoodworth.fastcraft.core.util.EventListener
import java.util.LinkedList

/**
 * A composite [GuiLayout].
 */
class GuiLayoutComposite(
        override val width: Int,
        override val height: Int
) : GuiLayout {

    override val changeListener = EventListener<EventGuiLayoutChange>()

    /** The layouts that compose this layout. */
    private val childLayouts = LinkedList<LayoutPosition>()

    /** The buttons within this layout */
    private val buttons = mutableMapOf<Pair<Int, Int>, GuiButton>()

    override fun getButton(x: Int, y: Int): GuiButton? {
        return buttons[Pair(x, y)] ?: childLayoutAtPoint(x, y)?.let {
            it.layout.getButton(it.x, it.y)
        }
    }

    override fun setButton(x: Int, y: Int, button: GuiButton) {
        removeButton(x, y)
        buttons[Pair(x, y)] = button
        changeListener += button.changeListener::notifyHandlers
    }

    override fun removeButton(x: Int, y: Int) {
        buttons.remove(Pair(x, y))?.let {
            changeListener -= it.changeListener::notifyHandlers
        }
    }

    /**
     * Add a child layout or button at the specified location.
     *
     *
     * @param layout the layout to add
     */
    fun addLayout(x: Int, y: Int, layout: GuiLayout) {
        childLayouts += LayoutPosition(x, y, this)
        layout.changeListener += changeListener::notifyHandlers
    }

    /**
     * Remove a child layout or button.
     */
    fun removeLayout(layout: GuiLayout) {
        childLayouts.filter {
            layout === it.layout
        }.forEach {
            childLayouts.remove(it)
            it.layout.changeListener -= changeListener::notifyHandlers
        }
    }

    /**
     * Get the layout at a point.
     *
     * @param x the x-coordinate within this layout
     * @param y the y-coordinate within this layout
     * @return the layout at the specified point, and the coordinates
     *         within that layout that these coordinates point to
     */
    private fun childLayoutAtPoint(x: Int, y: Int): LayoutPosition? {
        for (layoutPosition in childLayouts) {
            val xRange = (layoutPosition.x)..(layoutPosition.x + layoutPosition.layout.width)
            val yRange = (layoutPosition.y)..(layoutPosition.y + layoutPosition.layout.height)

            if (x in xRange && y in yRange) {
                val newX = x - layoutPosition.layout.width
                val newY = y - layoutPosition.layout.height
                return LayoutPosition(newX, newY, layoutPosition.layout)
            }
        }

        // No child layout at point
        return null
    }

    /**
     * Contains a layout, and a coordinate pair.
     */
    private class LayoutPosition(
            /** The x-coordinate within the layout. */
            val x: Int,

            /** The y-coordinate within the layout. */
            val y: Int,

            /** The layout. */
            val layout: GuiLayout
    )
}
