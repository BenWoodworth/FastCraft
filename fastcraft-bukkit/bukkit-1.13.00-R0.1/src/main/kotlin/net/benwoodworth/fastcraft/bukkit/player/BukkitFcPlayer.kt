package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.locale.BukkitFcLocale
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.getAs
import org.bukkit.entity.Player
import java.util.*

class BukkitFcPlayer(
    val player: Player
) : FcPlayer {

    override val username: String
        get() = player.name

    override var customName: String?
        get() = player.customName
        set(value) {
            player.customName = value
        }

    override val uuid: UUID
        get() = player.uniqueId

    override val locale: FcLocale
        get() = BukkitFcLocale(player.locale)

    override fun sendMessage(message: FcText) {
        val bukkitText = message.getAs<BukkitFcText>()

        player.sendRawMessage(bukkitText.toRaw())
    }

    override fun hasPermission(permission: String): Boolean {
        return player.hasPermission(permission)
    }
}
