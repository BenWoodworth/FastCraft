package net.benwoodworth.fastcraft.api.gui

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.event.EventGuiLayoutChange

/**
 * An object that can be added to a [Gui].
 */
interface GuiElement {

    /**
     * A listener for layout changes.
     */
    val changeListener: Listener<EventGuiLayoutChange>

    /**
     * The x-position of this element within the containing layout.
     */
    val x: Int

    /**
     * The y-position of this element within the containing layout.
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

    /**
     * Checks if this element contains a point.
     *
     * @param x the x-position to check
     * @param y the y-position to check
     * @return `true` if the specified point is contained within this element
     */
    fun contains(x: Int, y: Int) =
            this.x in x until (x + width) &&
            this.y in y until (y + height)
}