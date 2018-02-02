package net.benwoodworth.fastcraft.api.gui.button

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.event.GuiEventClick
import net.benwoodworth.fastcraft.api.gui.event.GuiEventLayoutChange
import net.benwoodworth.fastcraft.dependencies.item.Item

/**
 * An abstract implementation of [GuiButton].
 */
abstract class GuiButtonAbstract(item: Item? = null) : GuiButton {

    override val changeListener = Listener<GuiEventLayoutChange>()

    override val clickListener = Listener<GuiEventClick>().apply {
        addHandler(::onClick)
    }

    override var item = item
        protected set(value) {
            changeListener.notifyHandlers(GuiEventLayoutChange())
        }

    /**
     * Handles this button's clicks.
     *
     * @param event the click event
     */
    protected abstract fun onClick(event: GuiEventClick)
}
