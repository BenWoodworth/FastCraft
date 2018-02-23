package net.benwoodworth.fastcraft.dependencies.api.gui

import net.benwoodworth.fastcraft.dependencies.api.event.FcListener
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClose
import net.benwoodworth.fastcraft.dependencies.api.gui.layout.GuiLayoutRoot

/**
 * A user interface for in-game players.
 */
abstract class GuiAbstract : Gui {

    override val closeListener = FcListener<GuiEventClose>()

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
    protected class LayoutLocation(
            val layout: GuiLayoutRoot,
            val location: GuiLocation
    )
}
