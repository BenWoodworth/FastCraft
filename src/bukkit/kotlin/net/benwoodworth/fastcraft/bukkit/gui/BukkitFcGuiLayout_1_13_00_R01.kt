package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.text.FcLocale
import org.bukkit.inventory.Inventory

abstract class BukkitFcGuiLayout_1_13_00_R01(
    override val inventory: Inventory,
    private val guiButtonFactory: BukkitFcGuiButton_1_13_00_R01Factory
) : BukkitFcGuiLayout {

    protected abstract val locale: FcLocale

    private val buttons: MutableMap<Int, FcGuiButton> = mutableMapOf()

    override fun getSlotButton(slot: Int): FcGuiButton? {
        return buttons[slot]
    }

    protected fun getSlotButtonOrCreate(slot: Int): FcGuiButton {
        return buttons.getOrPut(slot) {
            guiButtonFactory.create(inventory, slot, locale)
        }
    }
}