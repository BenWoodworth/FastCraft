package net.benwoodworth.fastcraft.platform.api.gui.event

import net.benwoodworth.fastcraft.platform.api.gui.FcGuiRegion

/**
 * An event for changes in a GUI layout.
 */
data class GuiEventLayoutChange(
    val region: FcGuiRegion
)
