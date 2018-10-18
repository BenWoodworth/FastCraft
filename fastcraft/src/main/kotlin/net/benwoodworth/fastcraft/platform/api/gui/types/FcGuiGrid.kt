package net.benwoodworth.fastcraft.platform.api.gui.types

import net.benwoodworth.fastcraft.platform.api.gui.FcGui
import net.benwoodworth.fastcraft.platform.api.gui.GuiEventClick
import net.benwoodworth.fastcraft.platform.api.item.FcItem

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
        clickHandler: (event: GuiEventClick) -> Unit = {}
    )

    fun removeItem(row: Int, column: Int)
}
