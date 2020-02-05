package net.benwoodworth.fastcraft.bukkit.gui

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import org.bukkit.inventory.Inventory
import java.util.*

@AutoFactory
class BukkitFcGuiLayoutGrid_1_15_00_R01(
    override val width: Int,
    override val height: Int,
    inventory: Inventory,
    override val locale: Locale,
    @Provided private val guiButtonFactory: BukkitFcGuiButton_1_15_00_R01Factory
) : BukkitFcGuiLayout_1_15_00_R01(inventory, guiButtonFactory), BukkitFcGuiLayoutGrid {
    override fun getButton(column: Int, row: Int): FcGuiButton {
        return getSlotButtonOrCreate(row * width + column)
    }
}
