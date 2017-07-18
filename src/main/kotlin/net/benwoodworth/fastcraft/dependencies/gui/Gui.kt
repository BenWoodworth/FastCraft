package net.benwoodworth.fastcraft.dependencies.gui

import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * A user interface for in-game players.
 */
interface Gui : GuiLayoutComposite {

    /**
     * The title of this [Gui].
     */
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

    /**
     * A builder for [Gui]'s.
     */
    interface Builder {

        /**
         * Build the [Gui]
         *
         * @return the built [Gui]
         */
        fun build(): Gui

        /**
         * Set the [Gui] height.
         *
         * @return fluent interface
         */
        fun setHeight(height: Int): Gui.Builder

        /**
         * Set the [Gui] title.
         *
         * @return fluent interface
         */
        fun setTitle(title: Text): Gui.Builder
    }
}
