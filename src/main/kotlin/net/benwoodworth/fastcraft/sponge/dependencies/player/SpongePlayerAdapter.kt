package net.benwoodworth.fastcraft.sponge.dependencies.player

import net.benwoodworth.fastcraft.core.dependencies.inventory.InventoryAdapter
import net.benwoodworth.fastcraft.core.dependencies.permissions.PermissionAdapter
import net.benwoodworth.fastcraft.core.dependencies.player.PlayerAdapter
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.text.Text
import java.util.*

/**
 * Adapter for Sponge players.
 */
class SpongePlayerAdapter(
        private val basePlayer: Player
): PlayerAdapter(basePlayer) {

    override val username: String
        get() = basePlayer.name

    override var displayName: String?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override val uuid: UUID
        get() = basePlayer.uniqueId

    override fun sendMessage(message: String) {
        basePlayer.sendMessage(Text.of(message))
    }

    override fun hasPermission(permission: PermissionAdapter): Boolean {
        return basePlayer.hasPermission(permission.permissionString)
    }

    override fun openInventory(inventory: InventoryAdapter) {
        basePlayer.openInventory(inventory.unwrap(), null) // TODO Don't use null cause
    }

}
