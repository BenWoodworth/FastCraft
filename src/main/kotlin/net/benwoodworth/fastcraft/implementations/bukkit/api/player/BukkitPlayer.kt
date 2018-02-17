package net.benwoodworth.fastcraft.implementations.bukkit.api.player

import net.benwoodworth.fastcraft.dependencies.api.player.Player
import net.benwoodworth.fastcraft.dependencies.api.text.Text
import net.benwoodworth.fastcraft.implementations.bukkit.api.text.BukkitText
import net.benwoodworth.fastcraft.util.Adapter
import java.util.*
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
        get() = base.displayName?.let(::BukkitText)
        set(value) {
            base.displayName = (value as BukkitText?)?.text
        }

    override val uuid: UUID
        get() = base.uniqueId

    override fun sendMessage(message: Text) {
        base.sendMessage((message as BukkitText).text)
    }

    override fun hasPermission(permission: String): Boolean {
        return base.hasPermission(permission)
    }
}
