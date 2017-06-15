package net.benwoodworth.fastcraft.sponge.dependencies.player

import net.benwoodworth.fastcraft.core.dependencies.inventory.FcInventory
import net.benwoodworth.fastcraft.core.dependencies.permissions.FcPermission
import net.benwoodworth.fastcraft.core.dependencies.player.FcPlayer
import net.benwoodworth.fastcraft.sponge.dependencies.inventory.SpongeItem
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.text.Text
import java.util.*

/**
 * Adapter for Sponge players.
 */
class SpongePlayer(private val base: Player): FcPlayer<SpongeItem> {

    override val username: String
        get() = base.name

    override var displayName: String?
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}

    override val uuid: UUID
        get() = base.uniqueId

    override fun sendMessage(message: String) {
        base.sendMessage(Text.of(message))
    }

    override fun hasPermission(permission: FcPermission): Boolean {
        return base.hasPermission(permission.permissionString)
    }

    override fun openInventory(inventory: FcInventory<SpongeItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
