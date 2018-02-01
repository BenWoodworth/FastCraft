package net.benwoodworth.fastcraft.api.gui.layout

import net.benwoodworth.fastcraft.api.gui.element.GuiElementSizable

/**
 * A resizable GUI layout.
 */
class GuiLayoutSizable(
        x: Int,
        y: Int,
        width: Int,
        height: Int
) : GuiLayoutAbstract(
        x,
        y,
        width,
        height
), GuiElementSizable