package net.benwoodworth.fastcraft.dependencies.api.gui.element

import net.benwoodworth.fastcraft.dependencies.api.event.FcListener
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange

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