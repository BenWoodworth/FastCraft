package net.benwoodworth.fastcraft.api.gui

import net.benwoodworth.fastcraft.api.Listener
import net.benwoodworth.fastcraft.api.gui.event.GuiEventClose
import net.benwoodworth.fastcraft.api.gui.layout.GuiLayoutRoot
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.Text

/**
 * A user interface for in-game players.
 */
interface Gui {

    /**
     * The primary layout in this [Gui].
     */
    val layout: GuiLayoutRoot

    /**
     * A listener for the inventory closing.
     */
    val closeListener: Listener<GuiEventClose>

    /**
     * The title of this [Gui].
     */
    val title: Text

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
     * A chest [Gui], with a 9xN layout.
     */
    interface Chest : Gui

    /**
     * A dispenser [Gui], with a 3x3 layout.
     */
    interface Dispenser : Gui

    /**
     * A hopper [Gui], with a 5x1 layout.
     */
    interface Hopper : Gui
}
