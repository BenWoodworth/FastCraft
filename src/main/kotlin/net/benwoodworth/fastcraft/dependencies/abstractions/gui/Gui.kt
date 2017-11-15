package net.benwoodworth.fastcraft.dependencies.abstractions.gui

import net.benwoodworth.fastcraft.dependencies.abstractions.event.EventGuiClose
import net.benwoodworth.fastcraft.dependencies.abstractions.event.Listener
import net.benwoodworth.fastcraft.dependencies.abstractions.player.Player
import net.benwoodworth.fastcraft.dependencies.abstractions.text.Text

/**
 * A user interface for in-game players.
 */
interface Gui : GuiLayoutComposite {

    /**
     * A listener for the inventory closing.
     */
    val closeListener: Listener<EventGuiClose>

    /**
     * The title of this [Gui].
     */
    val title: Text?

    /**
     * Open this [Gui] for the given players.
     *
     * @param players the players to open the inventory for
     */
    fun open(vararg players: Player)

    /**
     * Get the players viewing the inventory.
     *
     * @return the players viewing the inventory
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
        fun height(height: Int): Gui.Builder

        /**
         * Set the [Gui] title.
         *
         * @return fluent interface
         */
        fun title(title: Text): Gui.Builder
    }
}
