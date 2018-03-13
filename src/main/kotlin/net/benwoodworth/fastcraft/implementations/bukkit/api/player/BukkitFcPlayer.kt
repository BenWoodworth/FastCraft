package net.benwoodworth.fastcraft.implementations.bukkit.api.player

import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.entity.Player
import java.util.*

/**
 * Bukkit implementation of [FcPlayer].
 */
class BukkitFcPlayer(
        override val base: Player
) : FcPlayer, Adapter<Player>() {

    override val username: String
        get() = base.name

    override var displayName: FcText?
        get() = base.displayName?.let(::BukkitFcText)
        set(value) {
            base.displayName = (value as BukkitFcText?)?.text
        }

    override val uuid: UUID
        get() = base.uniqueId

    override fun sendMessage(message: FcText) {
        base.sendMessage((message as BukkitFcText).text)
    }

    override fun hasPermission(permission: String): Boolean {
        return base.hasPermission(permission)
    }
}
