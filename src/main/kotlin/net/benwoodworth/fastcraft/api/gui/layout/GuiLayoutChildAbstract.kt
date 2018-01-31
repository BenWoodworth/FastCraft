package net.benwoodworth.fastcraft.api.gui.layout

import net.benwoodworth.fastcraft.api.gui.event.EventGuiLayoutChange

/**
 * The button layout of a GUI.
 */
abstract class GuiLayoutChildAbstract(
        x: Int,
        y: Int,
        width: Int,
        height: Int
) : GuiLayoutAbstract(width, height), GuiLayoutChild {

    override var x = x
        protected set(value) {
            field = value
            changeListener.notifyHandlers(EventGuiLayoutChange())
        }

    override var y = y
        protected set(value) {
            field = value
            changeListener.notifyHandlers(EventGuiLayoutChange())
        }
}
