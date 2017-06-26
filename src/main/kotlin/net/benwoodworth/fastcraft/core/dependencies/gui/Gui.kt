package net.benwoodworth.fastcraft.core.dependencies.gui

import net.benwoodworth.fastcraft.core.dependencies.player.Player
import net.benwoodworth.fastcraft.core.dependencies.text.Text

/**
 * A user interface for in-game players.
 */
interface Gui {

    /** The title of this [Gui]. */
    val title: Text

    /** The layout of this [Gui]. */
    var layout: GuiLayout

    /**
     * Open this [Gui] for the given players.
     */
    fun open(vararg player: Player)

    /**
     * Close this [Gui] for the given players.
     */
    fun close(vararg player: Player)

    /**
     * Get the players viewing this [Gui].
     *
     * @return a list of players viewing this [Gui]
     */
    fun getViewers(): List<Player>
}
