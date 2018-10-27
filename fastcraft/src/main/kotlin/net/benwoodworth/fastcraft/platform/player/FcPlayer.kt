package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.Extensible
import java.util.*

/**
 * An interface for Minecraft players.
 */
interface FcPlayer : Extensible {

    /**
     * The player's username.
     */
    val username: String

    /**
     * The player's display name.
     */
    var customName: String?

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
