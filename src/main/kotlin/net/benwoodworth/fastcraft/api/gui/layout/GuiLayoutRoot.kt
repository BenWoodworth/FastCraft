package net.benwoodworth.fastcraft.api.gui.layout

/**
 * The top-level layout within a GUI.
 */
class GuiLayoutRoot(
        width: Int,
        height: Int
) : GuiLayout by GuiLayoutBasic(
        0,
        0,
        width,
        height
)
