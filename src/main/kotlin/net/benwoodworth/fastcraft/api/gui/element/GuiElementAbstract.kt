package net.benwoodworth.fastcraft.api.gui.element

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.event.GuiEventLayoutChange

abstract class GuiElementAbstract(
        x: Int,
        y: Int,
        width: Int,
        height: Int
) : GuiElement {

    override val changeListener = Listener<GuiEventLayoutChange>()

    override var x = x
        set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange())
        }

    override var y = y
        set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange())
        }

    override var width = width
        set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange())
        }

    override var height = height
        set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange())
        }
}