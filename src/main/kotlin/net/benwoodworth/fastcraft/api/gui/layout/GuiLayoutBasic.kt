package net.benwoodworth.fastcraft.api.gui.layout

import net.benwoodworth.fastcraft.api.gui.element.GuiElement

class GuiLayoutBasic(
        x: Int,
        y: Int,
        width: Int,
        height: Int
) : GuiElement.Mutable, GuiLayoutAbstract(
        x,
        y,
        width,
        height
)