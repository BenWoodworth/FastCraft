package net.benwoodworth.fastcraft.api.gui.button

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.event.EventGuiButtonClick
import net.benwoodworth.fastcraft.api.gui.event.EventGuiLayoutChange
import net.benwoodworth.fastcraft.dependencies.item.Item

/**
 * An abstract implementation of [GuiButton].
 */
abstract class GuiButtonAbstract(item: Item? = null) : GuiButton {

    override val changeListener = Listener<EventGuiLayoutChange>()

    override val clickListener = Listener<EventGuiButtonClick>().apply {
        addHandler(::onClick)
    }

    override var item = item
        protected set(value) {
            changeListener.notifyHandlers(EventGuiLayoutChange())
        }

    /**
     * Handles this button's clicks.
     *
     * @param event the click event
     */
    protected abstract fun onClick(event: EventGuiButtonClick)
}
