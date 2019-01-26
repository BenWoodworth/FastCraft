package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import org.bukkit.inventory.Inventory

class BukkitFcGuiLayoutSlot_1_13_00_R01(
    private val inventory: Inventory,
    private val slotIndex: Int,
    private val itemFactory: FcItemFactory
) : BukkitFcGuiLayoutSlot {

    override var item: FcItem?
        get() = with(itemFactory.bukkit) {
            FcItem(inventory.getItem(slotIndex))
        }
        set(value) {
            inventory.setItem(slotIndex, value?.bukkit?.cloneItemStack())
        }

    override fun setClickHandler(column: Int, row: Int, handler: (event: FcGuiClickEvent) -> Unit) {
        TODO("not implemented")
    }

    override fun removeClickHandler(column: Int, row: Int) {
        TODO("not implemented")
    }
}