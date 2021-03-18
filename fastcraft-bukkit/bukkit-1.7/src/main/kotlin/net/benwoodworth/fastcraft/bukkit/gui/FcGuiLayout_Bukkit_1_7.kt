package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiButton
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayout
import org.bukkit.inventory.Inventory
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

abstract class FcGuiLayout_Bukkit_1_7(
    override val inventory: Inventory,
    private val guiButtonFactory: FcGuiButton_Bukkit.Factory,
) : FcGuiLayout_Bukkit {
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
        guiButtonFactory: FcGuiButton_Bukkit_1_7.Factory,
    ) : FcGuiLayout_Bukkit_1_7(inventory, guiButtonFactory), FcGuiLayout_Bukkit.Grid {
        override fun getButton(column: Int, row: Int): FcGuiButton {
            return getSlotButtonOrCreate(row * width + column)
        }
    }

    @Singleton
    class Factory @Inject constructor(
        private val fcGuiButtonFactory: FcGuiButton_Bukkit_1_7.Factory,
    ) : FcGuiLayout_Bukkit.Factory {
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
                guiButtonFactory = fcGuiButtonFactory
            )
        }
    }
}
