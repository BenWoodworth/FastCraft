package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.inventory.Inventory

class BukkitFcGuiButton_1_13_00_R01(
    private val inventory: Inventory,
    private val slotIndex: Int
) : BukkitFcGuiButton {

    override var clickHandler: ((event: FcGuiClickEvent) -> Unit)? = null

    override fun setItem(item: FcItem?) {
        inventory.setItem(slotIndex, item?.bukkit?.toItemStack())
    }
}