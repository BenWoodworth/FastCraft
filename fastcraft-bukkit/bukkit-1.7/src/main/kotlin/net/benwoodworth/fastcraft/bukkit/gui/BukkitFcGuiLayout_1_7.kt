package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayout
import org.bukkit.inventory.Inventory
import java.util.*
import javax.inject.Inject

abstract class BukkitFcGuiLayout_1_7(
    override val inventory: Inventory,
    private val guiButtonFactory: BukkitFcGuiButton.Factory,
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

    class Grid(
        override val width: Int,
        override val height: Int,
        inventory: Inventory,
        override val locale: Locale,
        guiButtonFactory: BukkitFcGuiButton_1_7.Factory,
    ) : BukkitFcGuiLayout_1_7(inventory, guiButtonFactory), BukkitFcGuiLayout.Grid {
        override fun getButton(column: Int, row: Int): FcGuiButton {
            return getSlotButtonOrCreate(row * width + column)
        }
    }

    class Factory @Inject constructor(
        private val guiButtonFactory: BukkitFcGuiButton_1_7.Factory,
    ) : BukkitFcGuiLayout.Factory {
        override fun createGridLayout(
            width: Int,
            height: Int,
            inventory: Inventory,
            locale: Locale,
        ): FcGuiLayout.Grid {
            return Grid(
                width = width,
                height = height,
                inventory = inventory,
                locale = locale,
                guiButtonFactory = guiButtonFactory
            )
        }
    }
}
