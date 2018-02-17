package net.benwoodworth.fastcraft.dependencies.api.gui.layout

import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElement

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