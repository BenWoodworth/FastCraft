package net.benwoodworth.fastcraft.dependencies.api.gui.element

import net.benwoodworth.fastcraft.dependencies.api.Listener
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.dependencies.api.gui.region.GuiPoint

abstract class GuiElementAbstract(
        location: GuiPoint
        width: Int,
        height: Int
) : GuiElement {

    override val changeListener = Listener<GuiEventLayoutChange>()

    private fun changePos(dx: Int, dy: Int) = changeListener.notifyHandlers(
            GuiEventLayoutChange(
                    GuiRegionConstruct()
                            .include(this)
                            .include(this, -dx, -dy)
            )
    )

    private fun changeSize(dw: Int, dh: Int) = changeListener.notifyHandlers(
            GuiEventLayoutChange(
                    GuiRegionConstruct()
                            .include(x, y, width, height)
                            .include(x, y, width - dw, height - dh)
            )
    )

    override var location: GuiPoint

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