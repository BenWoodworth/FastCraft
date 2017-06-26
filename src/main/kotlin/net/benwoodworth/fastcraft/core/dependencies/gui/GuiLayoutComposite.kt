package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.event.EventListener

/**
 * A composite [GuiLayout].
 */
class GuiLayoutComposite(
        override val width: Int,
        override val height: Int
) : GuiLayout {

    override val changeListener = EventListener<EventGuiLayoutChange>()

    /** The layouts that compose this layout. */
    private val childLayouts = ArrayList<LayoutPosition>()

    override fun getButton(x: Int, y: Int): GuiButton? {
        return childLayoutAtPoint(x, y)?.let {
            it.layout.getButton(it.x, it.y)
        }
    }

    override fun setButton(x: Int, y: Int, button: GuiButton) {
        addLayout(x, y, button)
    }

    /**
     * Add a child layout or button at the specified location.
     *
     * @param layout the layout to add
     */
    fun addLayout(x: Int, y: Int, layout: GuiLayout) {
        childLayouts += LayoutPosition(x, y, this)
        layout.changeListener.addHandler(changeListener::notifyHandlers)
    }

    /**
     * Remove a child layout or button.
     */
    fun removeLayout(layout: GuiLayout) {
        val layoutPosition = childLayouts.find { it.layout === layout } ?: return
        childLayouts.remove(layoutPosition)
        layout.changeListener.removeHandler(changeListener::notifyHandlers)
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
