package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.gui.events.EventGuiButtonClick
import net.benwoodworth.fastcraft.core.dependencies.gui.events.EventGuiLayoutChange
import net.benwoodworth.fastcraft.core.dependencies.item.Item
import net.benwoodworth.fastcraft.core.util.EventListener

/**
 * A button in a GUI.
 */
class GuiButton {

    val changeListener = EventListener<EventGuiLayoutChange>()

    /** The item representing this button. */
    var item: Item? = null
        get() = field?.copy()
        set(value) {
            field = value?.copy()
            changeListener.notifyHandlers(EventGuiLayoutChange())
        }

    /** A listener for button clicks. */
    val clickListener = EventListener<EventGuiButtonClick>()
}
