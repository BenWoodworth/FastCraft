package net.benwoodworth.fastcraft.core.api.gui

import net.benwoodworth.fastcraft.core.api.gui.layouts.GuiLayout
import net.benwoodworth.fastcraft.core.dependencies.item.inventory.ChestInventory

/**
 * A user interface for in-game players.
 */
class Gui(
        /*private val*/ inventory: ChestInventory
) : GuiLayout(inventory.width, inventory.height) {

    /**
     * Update the inventory to reflect the GUI.
     */
    private fun updateInventory() {
        for (x in 0 until width) {
            for (y in 0 until height) {
                // TODO Fix
                // inventory.setItem(x, y, getButton(x, y)?.buttonItem)
            }
        }
    }
}
