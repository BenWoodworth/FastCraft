package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.gui.events.EventGuiButtonClick
import net.benwoodworth.fastcraft.core.dependencies.gui.events.EventGuiLayoutChange
import net.benwoodworth.fastcraft.core.dependencies.item.Item
import net.benwoodworth.fastcraft.core.util.EventListener
import net.benwoodworth.fastcraft.core.util.Memento

/**
 * A button in a GUI.
 */
interface GuiButton {

    /** A listener for layout changes. */
    val changeListener: EventListener<EventGuiLayoutChange>

    /** A listener for button clicks. */
    val clickListener: EventListener<EventGuiButtonClick>

    /** The item representing this button. */
    var item: Memento<Item>?

    /**
     * Implementation of [GuiButton].
     */
    class Impl : GuiButton {

        override val changeListener = EventListener<EventGuiLayoutChange>()

        override val clickListener = EventListener<EventGuiButtonClick>()

        override var item: Memento<Item>? = null
            set(value) {
                field = value
                changeListener.notifyHandlers(EventGuiLayoutChange())
            }
    }
}
