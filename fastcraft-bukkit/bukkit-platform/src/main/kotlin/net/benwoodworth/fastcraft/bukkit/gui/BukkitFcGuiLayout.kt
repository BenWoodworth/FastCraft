package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayout
import org.bukkit.inventory.Inventory
import java.util.*

interface FcGuiLayout_Bukkit : FcGuiLayout {
    val inventory: Inventory

    fun getSlotButton(slot: Int): FcGuiButton?

    interface Grid : FcGuiLayout.Grid

    interface Factory {
        fun createGridLayout(
            width: Int,
            height: Int,
            inventory: Inventory,
            locale: Locale,
        ): FcGuiLayout.Grid
    }
}

val FcGuiLayout.inventory: Inventory
    get() = (this as FcGuiLayout_Bukkit).inventory

fun FcGuiLayout.getSlotButton(slot: Int): FcGuiButton? {
    return (this as FcGuiLayout_Bukkit).getSlotButton(slot)
}
