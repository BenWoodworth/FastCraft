package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import org.bukkit.inventory.Inventory

class BukkitFcGuiLayoutGrid_1_13_00_R01(
    override val width: Int,
    override val height: Int,
    inventory: Inventory
) : BukkitFcGuiLayout_1_13_00_R01(inventory), BukkitFcGuiLayoutGrid {

    override fun getButton(column: Int, row: Int): FcGuiButton {
        return getSlotButtonOrCreate(row * width + column)
    }
}
