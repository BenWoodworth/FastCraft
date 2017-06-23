package net.benwoodworth.fastcraft.core.api.gui.buttons

import net.benwoodworth.fastcraft.core.api.gui.events.GuiButtonClickEvent
import net.benwoodworth.fastcraft.core.api.gui.layouts.GuiLayout
import net.benwoodworth.fastcraft.core.dependencies.event.EventListener
import net.benwoodworth.fastcraft.core.dependencies.item.Item

/**
 * A button in a GUI.
 */
class GuiButton(
        /** The item representing this button */
        val buttonItem: Item? = null
) : GuiLayout(1, 1) {

    /** This button's click listener */
    val clickListener = EventListener<GuiButtonClickEvent>()
}
