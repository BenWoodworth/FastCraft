package net.benwoodworth.fastcraft.core.dependencies.player

import net.benwoodworth.fastcraft.core.dependencies.Adapter
import net.benwoodworth.fastcraft.core.dependencies.inventory.InventoryAdapter
import net.benwoodworth.fastcraft.core.dependencies.permissions.PermissionAdapter
import java.util.UUID

/**
 * An adapter for Minecraft players.
 */
abstract class PlayerAdapter(basePlayer: Any) : Adapter(basePlayer) {

    /** The player's username. */
    abstract val username: String

    /** The player's display name. */
    abstract var displayName: String?

    /** The player's UUID. */
    abstract val uuid: UUID

    /**
     * Send a message to the player.
     *
     * @param message The message to send.
     */
    abstract fun sendMessage(message: String)

    /**
     * See if the player has a permission.
     *
     * @param permission The permission to check.
     */
    abstract fun hasPermission(permission: PermissionAdapter): Boolean

    /**
     * Open an inventory.
     *
     * @param inventory The inventory to open.
     */
    abstract fun openInventory(inventory: InventoryAdapter)
}
