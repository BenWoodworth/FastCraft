package net.benwoodworth.fastcraft.dependencies.api.gui

import net.benwoodworth.fastcraft.dependencies.api.Listener
import net.benwoodworth.fastcraft.dependencies.api.gui.event.GuiEventClose
import net.benwoodworth.fastcraft.dependencies.api.gui.layout.GuiLayoutRoot
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.api.text.FcText

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
    val title: FcText?

    /**
     * Open this [Gui] for the given players.
     *
     * @param players the players to open the inventory for.
     */
    fun open(vararg players: FcPlayer)

    /**
     * Get the players viewing the inventory.
     *
     * @return the players viewing the inventory.
     */
    fun getViewers(): List<FcPlayer>

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
