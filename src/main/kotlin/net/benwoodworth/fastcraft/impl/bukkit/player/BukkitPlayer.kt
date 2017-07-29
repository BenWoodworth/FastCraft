package net.benwoodworth.fastcraft.impl.bukkit.player

import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.util.Adapter
import java.util.UUID
import org.bukkit.entity.Player as Bukkit_Player

/**
 * Bukkit implementation of [Player].
 */
class BukkitPlayer(
        basePlayer: Bukkit_Player
) : Player, Adapter<Bukkit_Player>(basePlayer) {

    override val username: String
        get() = base.name

    override var displayName: Text?
        get() = TODO("not implemented")
        set(value) {}

    override val uuid: UUID
        get() = base.uniqueId

    override fun sendMessage(message: Text) {
        TODO("not implemented")
    }

    override fun hasPermission(permission: String): Boolean {
        return base.hasPermission(permission)
    }
}
