package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import org.bukkit.inventory.Inventory

class BukkitFcGuiButton_1_13_00_R01(
    private val inventory: Inventory,
    private val slotIndex: Int,
    private val itemFactory: FcItemFactory
) : BukkitFcGuiButton {

    override var item: FcItem?
        get() = with(itemFactory.bukkit) {
            FcItem(inventory.getItem(slotIndex))
        }
        set(value) {
            inventory.setItem(slotIndex, value?.bukkit?.toItemStack())
        }

    override var clickHandler: ((event: FcGuiClickEvent) -> Unit)?
        get() = TODO("not implemented")
        set(value) {}
}