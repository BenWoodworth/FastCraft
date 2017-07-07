package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.gui.events.EventGuiLayoutChange
import net.benwoodworth.fastcraft.core.util.EventListener

/**
 * The button layout of a GUI.
 */
interface GuiLayout {

    /** The width of the layout. */
    val width: Int

    /** The height of the layout. */
    val height: Int

    /** A listener for layout changes. */
    val changeListener: EventListener<EventGuiLayoutChange>

    /**
     * Get a button at the specified position in the layout.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @return the button at the specified position, or null if there is none
     */
    fun getButton(x: Int, y: Int): GuiButton?

    /**
     * Add a button at the specified position in the layout.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param button the button to add
     * @return the button that was replaced, or `null` if there was none
     */
    fun setButton(x: Int, y: Int, button: GuiButton): GuiButton?

    /**
     * Remove a button from the specified position in the layout.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @return the button that was removed, or `null` if there was none
     */
    fun removeButton(x: Int, y: Int): GuiButton?
}
