package net.benwoodworth.fastcraft.platform.api.gui

import net.benwoodworth.fastcraft.platform.api.event.FcListener
import net.benwoodworth.fastcraft.platform.api.gui.event.GuiEventClose
import net.benwoodworth.fastcraft.platform.api.gui.layout.GuiLayoutRoot

/**
 * A user interface for in-game players.
 */
abstract class FcGuiBase : FcGui {

    override val closeListener = FcListener<GuiEventClose>()

    protected fun addLayout(width: Int, height: Int): GuiLayoutRoot {
        return GuiLayoutRoot(width, height).also {
            it.changeListener.addHandler { event ->
                updateLayout(event.region)
            }
        }
    }

    /**
     * Update the FcGui's layout.
     */
    protected abstract fun updateLayout(region: FcGuiRegion)

    /**
     * The position within a GUI's layout.
     */
    protected class LayoutLocation(
        val layout: GuiLayoutRoot,
        val position: FcGuiPosition
    )
}
