package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.gui.FcGuiLayoutSlot
import net.benwoodworth.fastcraft.platform.item.FcItem

class BukkitFcGuiLayoutSlot_1_13_00_R01 : BukkitFcGuiLayoutSlot {

    override val width: Int
        get() = TODO("not implemented")

    override val height: Int
        get() = TODO("not implemented")

    override fun get(column: Int, row: Int): FcGuiLayoutSlot {
        TODO("not implemented")
    }

    override var item: FcItem?
        get() = TODO("not implemented")
        set(value) {}

    override fun setClickHandler(column: Int, row: Int, handler: (event: FcGuiClickEvent) -> Unit) {
        TODO("not implemented")
    }

    override fun removeClickHandler(column: Int, row: Int) {
        TODO("not implemented")
    }
}