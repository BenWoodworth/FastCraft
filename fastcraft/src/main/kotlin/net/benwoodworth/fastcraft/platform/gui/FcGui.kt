package net.benwoodworth.fastcraft.platform.gui

import net.benwoodworth.fastcraft.platform.event.FcEventListener
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.util.Extensible

/**
 * A user interface for in-game players.
 */
interface FcGui : Extensible {

    /**
     * A listener for the inventory closing.
     */
    val onClose: FcEventListener<FcGuiCloseEvent>

    /**
     * Open this [FcGui] for the given players.
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
}
