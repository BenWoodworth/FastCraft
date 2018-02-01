package net.benwoodworth.fastcraft.api.gui

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.event.EventGuiClose
import net.benwoodworth.fastcraft.api.gui.layout.GuiLayoutRoot
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * A user interface for in-game players.
 */
abstract class GuiAbstract(
        override val title: Text? = null
) : Gui {

    override val closeListener = Listener<EventGuiClose>()

    protected fun addLayout(layout: GuiLayoutRoot) = layout.also {
        it.changeListener += ::updateLayout
    }
}
