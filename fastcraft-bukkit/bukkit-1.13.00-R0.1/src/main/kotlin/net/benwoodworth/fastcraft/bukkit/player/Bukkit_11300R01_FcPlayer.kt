package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.server.Bukkit_11300R01_FcLocale
import net.benwoodworth.fastcraft.bukkit.text.Bukkit_11300R01_FcText
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.server.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.util.getAs
import org.bukkit.entity.Player
import java.util.*

@Suppress("ClassName")
class Bukkit_11300R01_FcPlayer(
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
        get() = Bukkit_11300R01_FcLocale(player.locale)

    override fun sendMessage(message: FcText) {
        val bukkitText = message.getAs<Bukkit_11300R01_FcText>()

        player.sendRawMessage(bukkitText.toRaw())
    }

    override fun hasPermission(permission: String): Boolean {
        return player.hasPermission(permission)
    }
}
