package net.benwoodworth.fastcraft.platform.api.gui.layout

import net.benwoodworth.fastcraft.platform.api.gui.FcGuiRegion
import net.benwoodworth.fastcraft.platform.api.gui.element.GuiElement

class GuiLayoutBasic(
    region: FcGuiRegion.Positioned
) : GuiLayoutAbstract<FcGuiRegion.Positioned>(region), GuiElement.Mutable