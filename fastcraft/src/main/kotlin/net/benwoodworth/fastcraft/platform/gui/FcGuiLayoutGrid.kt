package net.benwoodworth.fastcraft.platform.gui

interface FcGuiLayoutGrid : FcGuiLayout {

    val width: Int

    val height: Int

    operator fun get(column: Int, row: Int): FcGuiLayoutSlot
}