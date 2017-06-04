package net.benwoodworth.fastcraft.bukkit.dependencies.player

import net.benwoodworth.fastcraft.bukkit.dependencies.inventory.BukkitItem
import net.benwoodworth.fastcraft.core.dependencies.inventory.FcInventory
import net.benwoodworth.fastcraft.core.dependencies.permissions.Permission
import net.benwoodworth.fastcraft.core.dependencies.player.FcPlayer
import org.bukkit.entity.Player
import java.util.*

/**
 * Created by ben on 6/4/17.
 */
class BukkitPlayer(private val basePlayer: Player): FcPlayer<BukkitItem> {

    override val username: String
        get() = basePlayer.name

    override var displayName: String?
        get() = basePlayer.displayName
        set(value) {
            basePlayer.displayName = value
        }

    override val uuid: UUID
        get() = basePlayer.uniqueId

    override fun sendMessage(message: String) {
        basePlayer.sendMessage(message)
    }

    override fun hasPermission(permission: Permission): Boolean {
        return basePlayer.hasPermission(permission.permissionString)
    }

    override fun openInventory(inventory: FcInventory<BukkitItem>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}