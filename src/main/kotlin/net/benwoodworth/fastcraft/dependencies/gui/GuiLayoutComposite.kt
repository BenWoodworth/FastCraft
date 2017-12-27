package net.benwoodworth.fastcraft.dependencies.gui

/**
 * A composite [GuiLayout].
 */
open class GuiLayoutComposite(
        width: Int,
        height: Int
) : GuiLayout(width, height) {

    /**
     * The layouts that compose this layout.
     */
    private val childLayouts = mutableListOf<LayoutPosition>()

    /**
     * Add a child layout or button at the specified location.
     *
     * @param layout the layout to add
     */
    fun addLayout(x: Int, y: Int, layout: GuiLayout) {
        childLayouts += LayoutPosition(x, y, layout)
        layout.changeListener += changeListener::notifyHandlers
        changeListener.notifyHandlers(EventGuiLayoutChange())
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
        changeListener.notifyHandlers(EventGuiLayoutChange())
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
        for (lp in childLayouts) {
            val xRange = (lp.x) until (lp.x + lp.layout.width)
            val yRange = (lp.y) until (lp.y + lp.layout.height)

            if (x in xRange && y in yRange) {
                val newX = x - lp.x
                val newY = y - lp.y
                return LayoutPosition(newX, newY, lp.layout)
            }
        }

        // No child layout at point
        return null
    }

    override fun getButton(x: Int, y: Int): GuiButton? {
        return super.getButton(x, y) ?: childLayoutAtPoint(x, y)?.let {
            it.layout.getButton(it.x, it.y)
        }
    }

    /**
     * Contains a layout, and a coordinate pair.
     */
    private class LayoutPosition(
            /**
             * The x-coordinate within the layout.
             */
            val x: Int,

            /**
             * The y-coordinate within the layout.
             */
            val y: Int,

            /**
             * The layout.
             */
            val layout: GuiLayout
    )
}
