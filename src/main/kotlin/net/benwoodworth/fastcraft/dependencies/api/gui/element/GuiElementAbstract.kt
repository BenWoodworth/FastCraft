package net.benwoodworth.fastcraft.dependencies.api.gui.element

import net.benwoodworth.fastcraft.dependencies.api.Listener
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.dependencies.api.gui.region.GuiRegion

abstract class GuiElementAbstract(region: GuiRegion) : GuiElement {

    override val changeListener = Listener<GuiEventLayoutChange>()

    override var region = region
        protected set(value) {
            val changeRegion = field.union(value)
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange(changeRegion))
        }
}