package net.benwoodworth.fastcraft.dependencies.gui.element

import net.benwoodworth.fastcraft.dependencies.event.FcListener
import net.benwoodworth.fastcraft.dependencies.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.gui.event.GuiEventLayoutChange

abstract class GuiElementAbstract<TRegion : GuiRegion.Positioned>(
        region: TRegion
) : GuiElement {

    override val changeListener = FcListener<GuiEventLayoutChange>()

    override var region = region
        protected set(value) {
            val changeRegion = field.union(value)
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange(changeRegion))
        }
}