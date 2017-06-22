package net.benwoodworth.fastcraft.sponge.dependencies.player

import net.benwoodworth.fastcraft.core.dependencies.inventory.Inventory
import net.benwoodworth.fastcraft.core.dependencies.permissions.Permissions
import net.benwoodworth.fastcraft.core.dependencies.player.Player
import net.benwoodworth.fastcraft.core.dependencies.text.Text
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import net.benwoodworth.fastcraft.sponge.dependencies.inventory.SpongeInventoryAdapter
import net.benwoodworth.fastcraft.sponge.dependencies.text.SpongeTextAdapter
import org.spongepowered.api.entity.living.player.Player as SpongePlayer
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
        get() = SpongeTextAdapter(base.displayNameData.displayName().get())
        set(value) {
            base.displayNameData.displayName().set(
                    (value as SpongeTextAdapter).base
            )
        }

    override val uuid: UUID
        get() = base.uniqueId

    override fun sendMessage(message: Text) {
        base.sendMessage(
                (message as SpongeTextAdapter).base
        )
    }

    override fun hasPermission(permission: Permissions): Boolean {
        return base.hasPermission(permission.permissionString)
    }

    override fun openInventory(inventory: Inventory) {
        base.openInventory((inventory as SpongeInventoryAdapter).base, null) // TODO Don't use null cause
    }

    override fun equals(other: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hashCode(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
