package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.player.Player
import net.benwoodworth.fastcraft.core.dependencies.text.Text

/**
 * A user interface for in-game players.
 */
interface Gui : GuiLayoutComposite {

    override var width: Int
        @Deprecated("Cannot change GUI size") set

    override var height: Int
        @Deprecated("Cannot change GUI size") set

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
