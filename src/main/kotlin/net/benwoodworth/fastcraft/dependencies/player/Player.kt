package net.benwoodworth.fastcraft.dependencies.player

import net.benwoodworth.fastcraft.dependencies.text.Text
import java.util.*

/**
 * An interface for Minecraft players.
 */
interface Player {

    /**
     * The player's username.
     */
    val username: String

    /**
     * The player's display name.
     */
    var displayName: Text?

    /**
     * The player's UUID.
     */
    val uuid: UUID

    /**
     * Send a message to the player.
     *
     * @param message The message to send.
     */
    fun sendMessage(message: Text)

    /**
     * See if the player has a permission.
     *
     * @param permission The permission to check.
     */
    fun hasPermission(permission: String): Boolean
}
