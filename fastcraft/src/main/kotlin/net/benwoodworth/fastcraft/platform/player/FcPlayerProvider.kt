package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.util.Extensible
import java.util.*

/**
 * Provides player for the plugin.
 */
interface FcPlayerProvider : Extensible {

    /**
     * Get a list of online player.
     *
     * @return a list of online players.
     */
    fun getOnlinePlayers(): List<FcPlayer>

    /**
     * Get an online player given a UUID.
     *
     * @param uuid the player's UUID.
     * @return the player with the given UUID, or null if none exists.
     */
    fun getPlayer(uuid: UUID): FcPlayer?
}
