package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.locale.BukkitFcLocale_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextConverter
import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

class BukkitFcPlayer_1_13_00_R01(
    override val uuid: UUID,
    private val textConverter: BukkitFcTextConverter
) : BukkitFcPlayer {

    override val player: Player = Bukkit.getPlayer(uuid)

    override val username: String
        get() = player.name

    override var customName: String?
        get() = player.customName
        set(value) {
            player.customName = value
        }

    override val locale: FcLocale
        get() = BukkitFcLocale_1_13_00_R01(player.locale)

    override fun sendMessage(message: FcText) {
        with(textConverter) {
            player.sendRawMessage(message.toRaw())
        }
    }

    override fun hasPermission(permission: String): Boolean {
        return player.hasPermission(permission)
    }
}
