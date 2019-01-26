package net.benwoodworth.fastcraft.bukkit.gui

import net.benwoodworth.fastcraft.platform.gui.FcGuiLayoutSlot

interface BukkitFcGuiLayoutSlot : FcGuiLayoutSlot {

    override val width: Int
        get() = 1

    override val height: Int
        get() = 1

    override fun get(column: Int, row: Int): FcGuiLayoutSlot {
        if (column == 0 && row == 0) {
            return this
        } else {
            throw IllegalArgumentException()
        }
    }
}