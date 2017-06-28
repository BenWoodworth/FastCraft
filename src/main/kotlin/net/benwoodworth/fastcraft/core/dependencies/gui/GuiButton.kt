package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.gui.events.EventGuiButtonClick
import net.benwoodworth.fastcraft.core.dependencies.gui.events.EventGuiLayoutChange
import net.benwoodworth.fastcraft.core.dependencies.item.Item
import net.benwoodworth.fastcraft.core.util.EventListener

/**
 * A button in a GUI.
 */
class GuiButton : GuiLayout {

    override val width = 1
    override val height = 1

    override val changeListener = EventListener<EventGuiLayoutChange>()

    /** The item representing this button. */
    var item: Item? = null
        get() = field?.copy()
        set(value) {
            field = value?.copy()
        }

    /** A listener for button clicks. */
    val clickListener = EventListener<EventGuiButtonClick>()

    override fun getButton(x: Int, y: Int): GuiButton? {
        return if (x == 0 && y == 0) this else null
    }

    override fun setButton(x: Int, y: Int, button: GuiButton) {
        throw UnsupportedOperationException()
    }
}
