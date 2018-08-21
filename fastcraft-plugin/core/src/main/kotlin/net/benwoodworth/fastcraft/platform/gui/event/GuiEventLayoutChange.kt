package net.benwoodworth.fastcraft.platform.gui.event

import net.benwoodworth.fastcraft.platform.gui.GuiRegion

/**
 * An event for changes in a GUI layout.
 */
data class GuiEventLayoutChange(
        val region: GuiRegion
)
