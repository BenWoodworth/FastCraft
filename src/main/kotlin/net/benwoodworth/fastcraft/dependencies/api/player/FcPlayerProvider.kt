package net.benwoodworth.fastcraft.dependencies.api.player

import java.util.*

/**
 * Provides player for the plugin.
 */
interface FcPlayerProvider {

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
    fun getOnlinePlayer(uuid: UUID): FcPlayer?
}
