package net.benwoodworth.fastcraft.dependencies.gui

import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * A user interface for in-game players.
 */
interface Gui : GuiLayoutComposite {

    /** The title of this [Gui]. */
    val title: Text?

    /**
     * Open this [Gui] for the given players.
     */
    fun open(vararg players: Player)

    /**
     * Get the players viewing this [Gui].
     *
     * @return a list of players viewing this [Gui]
     */
    fun getViewers(): List<Player>

    /**
     * Update the Gui's layout.
     */
    fun updateLayout()
}
