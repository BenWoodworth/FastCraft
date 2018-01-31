package net.benwoodworth.fastcraft.api.gui.button

import net.benwoodworth.fastcraft.api.gui.event.EventGuiButtonClick
import net.benwoodworth.fastcraft.dependencies.item.Item

/**
 * A basic implementation of [GuiButton].
 */
class GuiButtonBasic(item: Item? = null) : GuiButtonAbstract(item) {

    override var item: Item?
        get() = super.item
        set(value) {
            super.item = value
        }

    override fun onClick(event: EventGuiButtonClick) = Unit
}