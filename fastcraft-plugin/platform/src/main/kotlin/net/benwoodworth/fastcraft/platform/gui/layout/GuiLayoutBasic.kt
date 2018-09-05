package net.benwoodworth.fastcraft.platform.gui.layout

import net.benwoodworth.fastcraft.platform.gui.GuiRegion
import net.benwoodworth.fastcraft.platform.gui.element.GuiElement

class GuiLayoutBasic(
        region: GuiRegion.Positioned
) : GuiLayoutAbstract<GuiRegion.Positioned>(region), GuiElement.Mutable