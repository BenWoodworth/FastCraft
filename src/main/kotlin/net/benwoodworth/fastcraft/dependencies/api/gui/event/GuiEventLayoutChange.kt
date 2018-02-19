package net.benwoodworth.fastcraft.dependencies.api.gui.event

import net.benwoodworth.fastcraft.dependencies.api.gui.GuiRegion

/**
 * An event for changes in a GUI layout.
 */
data class GuiEventLayoutChange(
        val region: GuiRegion
)
