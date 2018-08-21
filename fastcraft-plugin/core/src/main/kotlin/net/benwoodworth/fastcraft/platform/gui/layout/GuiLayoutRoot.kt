package net.benwoodworth.fastcraft.platform.gui.layout

import net.benwoodworth.fastcraft.platform.gui.GuiRegion

/**
 * The top-level layout within a GUI.
 */
class GuiLayoutRoot(
        width: Int,
        height: Int
) : GuiLayoutAbstract<GuiRegion.Rectangle>(
        GuiRegion.Rectangle(0, 0, width, height)
)
