package net.benwoodworth.fastcraft.api.gui.region

interface GuiRegionRectangle : GuiRegion {

    /**
     * The distance from the left of the containing layout.
     */
    val x: Int

    /**
     * The distance from the top of the containing layout.
     */
    val y: Int

    /**
     * The width of this element.
     */
    val width: Int

    /**
     * The height of this element.
     */
    val height: Int

    override fun contains(x: Int, y: Int): Boolean {
        return (x in this.x until width) && (y in this.y until height)
    }
}