package net.benwoodworth.fastcraft.dependencies.gui

import net.benwoodworth.fastcraft.dependencies.event.Listener
import net.benwoodworth.fastcraft.dependencies.item.Item

/**
 * A button in a GUI.
 */
class GuiButtonBasic(
        item: Item? = null
) : GuiButton {

    /**
     * A listener for layout changes.
     */
    override val changeListener = Listener<EventGuiLayoutChange>()

    /**
     * A listener for button clicks.
     */
    override val clickListener = Listener<EventGuiButtonClick>()

    /**
     * The item representing this button.
     */
    override var item: Item? = item
        set(value) {
            field = value
            changeListener.notifyHandlers(EventGuiLayoutChange())
        }
}
