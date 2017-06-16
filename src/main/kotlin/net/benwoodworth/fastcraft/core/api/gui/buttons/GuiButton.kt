package net.benwoodworth.fastcraft.core.api.gui.buttons

import net.benwoodworth.fastcraft.core.api.gui.events.EventGuiButtonClick
import net.benwoodworth.fastcraft.core.api.gui.layouts.GuiLayout
import net.benwoodworth.fastcraft.core.dependencies.event.FcEventListener
import net.benwoodworth.fastcraft.core.dependencies.inventory.ItemAdapter

/**
 * A button in a GUI.
 */
class GuiButton(
        /** The item representing this button */
        val buttonItem: ItemAdapter? = null
) : GuiLayout(1, 1) {

    /** This button's click listener */
    val clickListener = FcEventListener<EventGuiButtonClick>()
}
