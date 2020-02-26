package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import org.bukkit.inventory.Inventory
import java.util.*

abstract class BukkitFcGuiLayout_1_8_R01(
    override val inventory: Inventory,
    private val guiButtonFactory: BukkitFcGuiButton.Factory
) : BukkitFcGuiLayout {
    protected abstract val locale: Locale

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
