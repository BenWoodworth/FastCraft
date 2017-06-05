package net.benwoodworth.fastcraft.core.api.gui.buttons

import net.benwoodworth.fastcraft.core.api.gui.events.EventGuiButtonClick
import net.benwoodworth.fastcraft.core.api.gui.layouts.GuiLayout
import net.benwoodworth.fastcraft.core.dependencies.event.FcEventListener
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem

/**
 * A button in a GUI.
 *
 * @param TItem the item type
 */
class GuiButton<TItem : FcItem<*>>(

        /** The item representing this button */
        val buttonItem: TItem? = null
) : GuiLayout<TItem>(1, 1) {

    /** This button's click listener */
    val clickListener = FcEventListener<EventGuiButtonClick<TItem>>()
}
