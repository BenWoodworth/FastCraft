package net.benwoodworth.fastcraft.dependencies.gui.button

import net.benwoodworth.fastcraft.dependencies.gui.GuiLocation
import net.benwoodworth.fastcraft.dependencies.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.gui.element.GuiElement
import net.benwoodworth.fastcraft.dependencies.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.dependencies.item.FcItem

/**
 * A basic implementation of [GuiButton].
 */
class GuiButtonBasic(
        region: GuiRegion.Positioned,
        item: FcItem? = null
) : GuiButtonAbstract(region), GuiElement.Mutable {

    /**
     * The item representing this button.
     */
    var item = item
        set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange(region))
        }

    override fun getItem(location: GuiLocation) = item

    override fun click(event: GuiEventClick) = Unit

    override fun onClick(event: GuiEventClick) = Unit
}