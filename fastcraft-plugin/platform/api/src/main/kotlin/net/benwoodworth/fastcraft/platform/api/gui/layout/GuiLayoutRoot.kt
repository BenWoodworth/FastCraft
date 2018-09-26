package net.benwoodworth.fastcraft.platform.api.gui.layout

import net.benwoodworth.fastcraft.platform.api.gui.FcGuiRegion

/**
 * The top-level layout within a GUI.
 */
class GuiLayoutRoot(
        width: Int,
        height: Int
) : GuiLayoutAbstract<FcGuiRegion.Rectangle>(
        FcGuiRegion.Rectangle(0, 0, width, height)
)
