package net.benwoodworth.fastcraft.sponge.dependencies.player

import net.benwoodworth.fastcraft.core.dependencies.inventory.Inventory
import net.benwoodworth.fastcraft.core.dependencies.permissions.Permissions
import net.benwoodworth.fastcraft.core.dependencies.player.Player
import net.benwoodworth.fastcraft.core.dependencies.text.Text
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import net.benwoodworth.fastcraft.sponge.dependencies.inventory.SpongeInventoryAdapter
import org.spongepowered.api.entity.living.player.Player as SpongePlayer
import org.spongepowered.api.text.Text as SpongeText
import java.util.UUID

/**
 * Adapter for Sponge players.
 */
class SpongePlayerAdapter(
        basePlayer: SpongePlayer
) : Player, Adapter<SpongePlayer>(basePlayer) {

    override val username: String
        get() = base.name

    override var displayName: Text?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override val uuid: UUID
        get() = base.uniqueId

    override fun sendMessage(message: Text) {
        base.sendMessage(SpongeText.of(message)) // TODO Use sponge text adapter
    }

    override fun hasPermission(permission: Permissions): Boolean {
        return base.hasPermission(permission.permissionString)
    }

    override fun openInventory(inventory: Inventory) = throw Exception()

    fun openInventory(inventory: SpongeInventoryAdapter) {
        base.openInventory(inventory.base, null) // TODO Don't use null cause
    }
}
