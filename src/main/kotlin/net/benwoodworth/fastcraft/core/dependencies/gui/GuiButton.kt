package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.gui.GuiButtonClickEvent
import net.benwoodworth.fastcraft.core.dependencies.gui.GuiLayout
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
