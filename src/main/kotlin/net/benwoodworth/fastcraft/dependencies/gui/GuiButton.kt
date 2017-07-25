package net.benwoodworth.fastcraft.dependencies.gui

import net.benwoodworth.fastcraft.dependencies.event.EventGuiButtonClick
import net.benwoodworth.fastcraft.dependencies.event.EventGuiLayoutChange
import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.event.Listener
import net.benwoodworth.fastcraft.util.Memento

/**
 * A button in a GUI.
 */
interface GuiButton {

    /**
     * A listener for layout changes.
     */
    val changeListener: Listener<EventGuiLayoutChange>

    /**
     * A listener for button clicks.
     */
    val clickListener: Listener<EventGuiButtonClick>

    /**
     * The item representing this button.
     */
    var item: Memento<Item>?

    /**
     * Implementation of [GuiButton].
     */
    class Impl : GuiButton {

        override val changeListener = Listener.Impl<EventGuiLayoutChange>()

        override val clickListener = Listener.Impl<EventGuiButtonClick>()

        override var item: Memento<Item>? = null
            set(value) {
                field = value
                changeListener.notifyHandlers(EventGuiLayoutChange.Impl())
            }
    }
}
