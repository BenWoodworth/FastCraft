package net.benwoodworth.fastcraft.dependencies.player

import java.util.UUID

/**
 * Provides player for the plugin.
 */
interface PlayerProvider {

    /**
     * Get a list of online player.
     *
     * @return A list of online players
     */
    fun getOnlinePlayers(): List<Player>

    /**
     * Get an online player given a UUID.
     *
     * @param uuid The player's UUID
     * @return The player with the given UUID, or null if none exists
     */
    fun getOnlinePlayer(uuid: UUID): Player?

    /**
     * Get an online player given a name.
     *
     * @param name The player's name
     * @return The player with the given UUID, or null if none exists
     */
    fun getOnlinePlayer(name: String): Player?
}
