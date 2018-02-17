package net.benwoodworth.fastcraft.api.gui.event

import net.benwoodworth.fastcraft.api.gui.region.GuiRegion

/**
 * An event for changes in a GUI layout.
 */
data class GuiEventLayoutChange(
        val region: GuiRegion
)
