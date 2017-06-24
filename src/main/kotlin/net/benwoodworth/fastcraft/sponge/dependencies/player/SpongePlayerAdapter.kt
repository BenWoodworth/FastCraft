package net.benwoodworth.fastcraft.sponge.dependencies.player

import net.benwoodworth.fastcraft.core.dependencies.item.inventory.Inventory
import net.benwoodworth.fastcraft.core.dependencies.permission.Permission
import net.benwoodworth.fastcraft.core.dependencies.player.Player
import net.benwoodworth.fastcraft.core.dependencies.text.Text
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import net.benwoodworth.fastcraft.sponge.dependencies.item.inventory.SpongeInventoryAdapter
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

    override fun hasPermission(permission: Permission): Boolean {
        return base.hasPermission(permission.id)
    }

    override fun openInventory(inventory: Inventory) {
        base.openInventory((inventory as SpongeInventoryAdapter).base, null) // TODO Don't use null cause
    }
}
