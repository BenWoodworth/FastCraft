package net.benwoodworth.fastcraft.api.gui.element

class GuiElementPositioned(
        val x: Int,
        val y: Int,
        val element: GuiElement
) : GuiElement by element {

    /**
     * Checks if this element contains a point.
     *
     * @param x the x-position to check
     * @param y the y-position to check
     * @return `true` if the specified point is contained within this element
     */
    fun contains(x: Int, y: Int) = (this.x in x until (x + width)) && (this.y in y until (y + height))
}