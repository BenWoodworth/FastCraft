package net.benwoodworth.fastcraft.dependencies.api.gui.button

import net.benwoodworth.fastcraft.dependencies.api.gui.GuiLocation
import net.benwoodworth.fastcraft.dependencies.api.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElement
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.dependencies.api.item.FcItem

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