package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.bukkit.bukkitRawText
import org.bukkit.entity.Player
import java.util.*

@Suppress("ClassName")
class `BukkitFcPlayer$1_13_R0_1`(
    private val bukkitPlayer: Player
) : net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayer {

    override val `bukkitPlayer$1_13_R0_1`: Any
        get() = bukkitPlayer

    override val username: String
        get() = bukkitPlayer.name

    override var customName: String?
        get() = bukkitPlayer.customName
        set(value) {
            bukkitPlayer.customName = value
        }

    override val uuid: UUID
        get() = bukkitPlayer.uniqueId

    override fun sendMessage(message: FcText) {
        bukkitPlayer.sendRawMessage(message.bukkitRawText)
    }

    override fun hasPermission(permission: String): Boolean {
        return bukkitPlayer.hasPermission(permission)
    }
}
