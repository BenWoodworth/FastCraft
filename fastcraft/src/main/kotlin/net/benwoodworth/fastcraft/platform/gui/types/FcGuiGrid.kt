package net.benwoodworth.fastcraft.platform.gui.types

import net.benwoodworth.fastcraft.platform.gui.FcGui
import net.benwoodworth.fastcraft.platform.gui.FcGuiClickEvent
import net.benwoodworth.fastcraft.platform.item.FcItem

/**
 * A user interface for in-game players.
 */
interface FcGuiGrid : FcGui {

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
