package net.benwoodworth.fastcraft.platform.gui

interface FcGuiLayout {
    interface Grid : FcGuiLayout {
        val width: Int
        val height: Int
        fun getButton(column: Int, row: Int): FcGuiButton
    }
}