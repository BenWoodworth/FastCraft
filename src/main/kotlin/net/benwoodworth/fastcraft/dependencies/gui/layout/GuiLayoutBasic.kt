package net.benwoodworth.fastcraft.dependencies.gui.layout

import net.benwoodworth.fastcraft.dependencies.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.gui.element.GuiElement

class GuiLayoutBasic(
        region: GuiRegion.Positioned
) : GuiLayoutAbstract<GuiRegion.Positioned>(region), GuiElement.Mutable