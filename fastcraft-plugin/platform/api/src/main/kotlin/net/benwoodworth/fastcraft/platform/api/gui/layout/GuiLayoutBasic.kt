package net.benwoodworth.fastcraft.platform.api.gui.layout

import net.benwoodworth.fastcraft.platform.api.gui.GuiRegion
import net.benwoodworth.fastcraft.platform.api.gui.element.GuiElement

class GuiLayoutBasic(
        region: GuiRegion.Positioned
) : GuiLayoutAbstract<GuiRegion.Positioned>(region), GuiElement.Mutable