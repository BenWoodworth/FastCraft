package net.benwoodworth.fastcraft.implementations.bukkit.api.player

import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.api.text.BukkitFcText
import net.benwoodworth.fastcraft.util.Adapter
import java.util.*
import org.bukkit.entity.Player as Bukkit_Player

/**
 * Bukkit implementation of [FcPlayer].
 */
class BukkitFcPlayer(
        basePlayer: Bukkit_Player
) : FcPlayer, Adapter<Bukkit_Player>(basePlayer) {

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
