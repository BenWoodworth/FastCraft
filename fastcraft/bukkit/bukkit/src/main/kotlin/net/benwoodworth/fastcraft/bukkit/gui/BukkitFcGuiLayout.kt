package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayout
import org.bukkit.inventory.Inventory

interface BukkitFcGuiLayout : FcGuiLayout {

    val inventory: Inventory

    fun getSlotButton(slot: Int): FcGuiButton?
}
