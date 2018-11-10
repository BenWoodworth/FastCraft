package net.benwoodworth.fastcraft.platform.gui.types

import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.util.Extensible

interface FcGuiGrid : Extensible, FcGui {

    val width: Int
    val height: Int

    fun setItem(
        row: Int,
        column: Int,
        item: FcItem,
        clickHandler: (event: FcGuiClickEvent) -> Unit = {}
    )

    fun removeItem(row: Int, column: Int)
}
