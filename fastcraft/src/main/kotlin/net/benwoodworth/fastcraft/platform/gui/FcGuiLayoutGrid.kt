package net.benwoodworth.fastcraft.platform.gui

interface FcGuiLayoutGrid : FcGuiLayout {

    val width: Int

    val height: Int

    fun getButton(column: Int, row: Int): FcGuiButton
}