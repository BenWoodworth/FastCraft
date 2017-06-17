package net.benwoodworth.fastcraft.core.dependencies.player

import net.benwoodworth.fastcraft.core.dependencies.inventory.Inventory
import net.benwoodworth.fastcraft.core.dependencies.permissions.Permissions
import java.util.UUID

/**
 * An interface for Minecraft players.
 */
interface Player {

    /** The player's username. */
    val username: String

    /** The player's display name. */
    var displayName: String?

    /** The player's UUID. */
    val uuid: UUID

    /**
     * Send a message to the player.
     *
     * @param message The message to send.
     */
    fun sendMessage(message: String)

    /**
     * See if the player has a permission.
     *
     * @param permission The permission to check.
     */
    fun hasPermission(permission: Permissions): Boolean

    /**
     * Open an inventory.
     *
     * @param inventory The inventory to open.
     */
    fun openInventory(inventory: Inventory)
}
