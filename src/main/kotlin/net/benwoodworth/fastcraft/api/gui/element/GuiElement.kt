package net.benwoodworth.fastcraft.api.gui.element

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.event.EventGuiLayoutChange

/**
 * An object that can be added to a [Gui].
 */
interface GuiElement {

    /**
     * A listener for element changes.
     */
    val changeListener: Listener<EventGuiLayoutChange>

    /**
     * The width of this element.
     */
    val width: Int

    /**
     * The height of this element.
     */
    val height: Int
}