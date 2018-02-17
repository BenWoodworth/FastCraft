package net.benwoodworth.fastcraft.dependencies.api.gui.element

import net.benwoodworth.fastcraft.dependencies.api.Listener
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange

abstract class GuiElementAbstract(
        x: Int,
        y: Int,
        width: Int,
        height: Int
) : GuiElement {

    override val changeListener = Listener<GuiEventLayoutChange>()

    override var x = x
        protected set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange(this))//TODO update old region
        }

    override var y = y
        protected set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange(this))
        }

    override var width = width
        protected set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange(this))
        }

    override var height = height
        protected set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange(this))
        }
}