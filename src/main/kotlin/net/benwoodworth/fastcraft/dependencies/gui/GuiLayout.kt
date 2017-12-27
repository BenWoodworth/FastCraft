package net.benwoodworth.fastcraft.dependencies.gui

import net.benwoodworth.fastcraft.dependencies.event.Listener

/**
 * The button layout of a GUI.
 */
open class GuiLayout(
        /**
         * The width of the layout.
         */
        val width: Int,

        /**
         * The height of the layout.
         */
        val height: Int
) {
    /**
     * The buttons within this layout.
     */
    private val buttons = mutableMapOf<Pair<Int, Int>, GuiButton>()

    /**
     * A listener for layout changes.
     */
    val changeListener = Listener<EventGuiLayoutChange>()

    /**
     * Get a button at the specified position in the layout.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @return the button at the specified position, or null if there is none
     */
    open fun getButton(x: Int, y: Int) = buttons[Pair(x, y)]

    /**
     * Add a button at the specified position in the layout.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @param button the button to add
     * @return the button that was replaced, or `null` if there was none
     */
    fun setButton(x: Int, y: Int, button: GuiButton): GuiButton? {
        return removeButtonNoNotify(x, y).also {
            buttons[Pair(x, y)] = button
            button.changeListener += changeListener::notifyHandlers
            changeListener.notifyHandlers(EventGuiLayoutChange())
        }
    }

    /**
     * Remove a button from the specified position in the layout.
     *
     * @param x the x-coordinate of the button
     * @param y the y-coordinate of the button
     * @return the button that was removed, or `null` if there was none
     */
    fun removeButton(x: Int, y: Int): GuiButton? {
        return removeButtonNoNotify(x, y)?.also {
            changeListener.notifyHandlers(EventGuiLayoutChange())
        }
    }

    /**
     * Remove a button without notifying the change listener.
     *
     * @return the removed button, or `null` if there was none
     */
    private fun removeButtonNoNotify(x: Int, y: Int): GuiButton? {
        return buttons.remove(Pair(x, y))?.also {
            it.changeListener -= changeListener::notifyHandlers
        }
    }
}
