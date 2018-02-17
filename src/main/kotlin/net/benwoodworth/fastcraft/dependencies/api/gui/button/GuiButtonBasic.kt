package net.benwoodworth.fastcraft.dependencies.api.gui.button

import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElement
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.dependencies.api.item.Item

/**
 * A basic implementation of [GuiButton].
 */
class GuiButtonBasic(
        x: Int,
        y: Int,
        item: Item? = null
) : GuiElement.Mutable, GuiButtonAbstract(x, y) {

    /**
     * The item representing this button.
     */
    var item = item
        set(value) {
            field = value
            changeListener.notifyHandlers(GuiEventLayoutChange(this))
        }

    override fun getItem(x: Int, y: Int) = item

    override fun onClick(event: GuiEventClick) = Unit
}