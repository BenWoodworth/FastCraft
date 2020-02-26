package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayout
import org.bukkit.inventory.Inventory

interface BukkitFcGuiLayout : FcGuiLayout {
    val inventory: Inventory

    fun getSlotButton(slot: Int): FcGuiButton?
}

val FcGuiLayout.inventory: Inventory
    get() = (this as BukkitFcGuiLayout).inventory

fun FcGuiLayout.getSlotButton(slot: Int): FcGuiButton? {
    return (this as BukkitFcGuiLayout).getSlotButton(slot)
}
