package net.benwoodworth.fastcraft.bukkit.gui

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextConverter
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.Inventory

@AutoFactory
class BukkitFcGuiLayoutGrid_1_13_00_R01(
    override val width: Int,
    override val height: Int,
    inventory: Inventory,
    override val locale: FcLocale,
    @Provided private val guiButtonFactory: BukkitFcGuiButton_1_13_00_R01Factory
) : BukkitFcGuiLayout_1_13_00_R01(inventory, guiButtonFactory), BukkitFcGuiLayoutGrid {

    override fun getButton(column: Int, row: Int): FcGuiButton {
        return getSlotButtonOrCreate(row * width + column)
    }
}
