package net.benwoodworth.fastcraft.dependencies.gui

import net.benwoodworth.fastcraft.dependencies.event.EventGuiButtonClick
import net.benwoodworth.fastcraft.dependencies.event.EventGuiLayoutChange
import net.benwoodworth.fastcraft.dependencies.event.Listener
import net.benwoodworth.fastcraft.dependencies.item.Item

/**
 * A button in a GUI.
 */
class GuiButton {

    /**
     * A listener for layout changes.
     */
    val changeListener = Listener<EventGuiLayoutChange>()

    /**
     * A listener for button clicks.
     */
    val clickListener = Listener<EventGuiButtonClick>()

    /**
     * The item representing this button.
     */
    var item: Item? = null
        set(value) {
            field = value
            changeListener.notifyHandlers(EventGuiLayoutChange.Impl())
        }
}
