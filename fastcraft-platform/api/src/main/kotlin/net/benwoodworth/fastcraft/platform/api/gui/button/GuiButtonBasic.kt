package net.benwoodworth.fastcraft.platform.api.gui.button

import net.benwoodworth.fastcraft.platform.api.gui.FcGuiPosition
import net.benwoodworth.fastcraft.platform.api.gui.FcGuiRegion
import net.benwoodworth.fastcraft.platform.api.gui.element.GuiElement
import net.benwoodworth.fastcraft.platform.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.platform.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.platform.api.item.FcItem

/**
 * A basic implementation of [GuiButton].
 */
class GuiButtonBasic(
        region: FcGuiRegion.Positioned,
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

    override fun getItem(position: FcGuiPosition) = item

    override fun click(event: GuiEventClick) = Unit

    override fun onClick(event: GuiEventClick) = Unit
}