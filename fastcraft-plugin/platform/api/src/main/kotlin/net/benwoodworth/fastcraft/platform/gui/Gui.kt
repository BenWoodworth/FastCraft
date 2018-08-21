package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.event.FcListener
import net.benwoodworth.fastcraft.platform.gui.event.GuiEventClose
import net.benwoodworth.fastcraft.platform.gui.layout.GuiLayoutRoot
import net.benwoodworth.fastcraft.platform.player.FcPlayer

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
    val closeListener: FcListener<GuiEventClose>

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
