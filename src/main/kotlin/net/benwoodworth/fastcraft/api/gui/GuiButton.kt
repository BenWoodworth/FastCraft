package net.benwoodworth.fastcraft.api.gui

import net.benwoodworth.fastcraft.dependencies.event.Listener
import net.benwoodworth.fastcraft.dependencies.item.Item

/**
 * A button in a GUI.
 */
open class GuiButton(item: Item? = null) {

    /**
     * A listener for layout changes.
     */
    val changeListener = Listener<EventGuiLayoutChange>()

    /**
     * A listener for button clicks.
     */
    val clickListener = Listener<EventGuiButtonClick>().apply {
        this += this@GuiButton::onClick
    }

    /**
     * The item representing this button.
     */
    var item: Item? = item
        set(value) {
            field = value
            changeListener.notifyHandlers(EventGuiLayoutChange())
        }

    /**
     * Handles this button's clicks.
     *
     * @param event the click event
     */
    open protected fun onClick(event: EventGuiButtonClick) {}
}
