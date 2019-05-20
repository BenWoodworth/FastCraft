package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemConverter
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.text.FcLocale
import org.bukkit.inventory.Inventory

class BukkitFcGuiLayoutGrid_1_13_00_R01(
    override val width: Int,
    override val height: Int,
    inventory: Inventory,
    override val locale: FcLocale,
    itemConverter: BukkitFcItemConverter
) : BukkitFcGuiLayout_1_13_00_R01(inventory, itemConverter), BukkitFcGuiLayoutGrid {

    override fun getButton(column: Int, row: Int): FcGuiButton {
        return getSlotButtonOrCreate(row * width + column)
    }
}
