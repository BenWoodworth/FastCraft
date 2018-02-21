package net.benwoodworth.fastcraft.dependencies.api.gui.layout

import net.benwoodworth.fastcraft.dependencies.api.gui.GuiRegion
import net.benwoodworth.fastcraft.dependencies.api.gui.element.GuiElement

class GuiLayoutBasic(
        region: GuiRegion.Positioned
) : GuiLayoutAbstract<GuiRegion.Positioned>(region), GuiElement.Mutable