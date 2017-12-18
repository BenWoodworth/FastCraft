package net.benwoodworth.fastcraft.dependencies.gui

import net.benwoodworth.fastcraft.dependencies.event.EventGuiClose
import net.benwoodworth.fastcraft.dependencies.event.Listener
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * A user interface for in-game players.
 */
abstract class Gui(
        height: Int
) : GuiLayoutComposite(9, height) {

    init {
        changeListener += ::updateLayout
    }

    /**
     * A listener for the inventory closing.
     */
    val closeListener = Listener<EventGuiClose>()

    /**
     * The title of this [Gui].
     */
    abstract val title: Text?

    /**
     * Open this [Gui] for the given players.
     *
     * @param players the players to open the inventory for
     */
    abstract fun open(vararg players: Player)

    /**
     * Get the players viewing the inventory.
     *
     * @return the players viewing the inventory
     */
    abstract fun getViewers(): List<Player>

    /**
     * Update the Gui's layout.
     */
    abstract fun updateLayout()

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
        fun height(height: Int): Builder

        /**
         * Set the [Gui] title.
         *
         * @return fluent interface
         */
        fun title(title: Text): Builder
    }
}