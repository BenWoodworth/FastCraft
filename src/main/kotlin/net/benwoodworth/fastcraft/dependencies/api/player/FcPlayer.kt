package net.benwoodworth.fastcraft.dependencies.api.player

import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import java.util.*

/**
 * An interface for Minecraft players.
 */
interface FcPlayer {

    /**
     * The player's username.
     */
    val username: String

    /**
     * The player's display name.
     */
    var displayName: FcText?

    /**
     * The player's UUID.
     */
    val uuid: UUID

    /**
     * Send a message to the player.
     *
     * @param message The message to send.
     */
    fun sendMessage(message: FcText)

    /**
     * See if the player has a permission.
     *
     * @param permission The permission to check.
     */
    fun hasPermission(permission: String): Boolean
}
