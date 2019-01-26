package net.benwoodworth.fastcraft.platform.gui.layout

interface FcGuiLayoutGrid : FcGuiLayout {

    val width: Int

    val height: Int

    operator fun get(column: Int, row: Int): FcGuiLayoutSlot
}