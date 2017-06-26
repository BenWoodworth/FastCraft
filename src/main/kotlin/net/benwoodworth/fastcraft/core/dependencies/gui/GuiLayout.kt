package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.event.EventListener
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventGuiLayoutChange

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
     */
    fun setButton(x: Int, y: Int, button: GuiButton)
}
