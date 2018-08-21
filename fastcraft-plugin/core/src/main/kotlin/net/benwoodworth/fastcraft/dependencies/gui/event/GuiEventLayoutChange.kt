package net.benwoodworth.fastcraft.dependencies.gui.event

import net.benwoodworth.fastcraft.dependencies.gui.GuiRegion

/**
 * An event for changes in a GUI layout.
 */
data class GuiEventLayoutChange(
        val region: GuiRegion
)
