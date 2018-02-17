package net.benwoodworth.fastcraft.api.gui

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.event.GuiEventClose
import net.benwoodworth.fastcraft.api.gui.layout.GuiLayoutRoot
import net.benwoodworth.fastcraft.api.gui.region.GuiRegion

/**
 * A user interface for in-game players.
 */
abstract class GuiAbstract : Gui {

    override val closeListener = Listener<GuiEventClose>()

    protected fun addLayout(width: Int, height: Int): GuiLayoutRoot {
        return GuiLayoutRoot(width, height).also {
            it.changeListener.addHandler { event ->
                updateLayout(event.region)
            }
        }
    }

    /**
     * Update the Gui's layout.
     */
    protected abstract fun updateLayout(region: GuiRegion)

    /**
     * The position within a GUI's layout.
     */
    protected class LayoutPos(
            val layout: GuiLayoutRoot,
            val x: Int,
            val y: Int
    )
}
