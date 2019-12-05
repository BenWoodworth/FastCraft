package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.bukkit.text.BukkitFcLocale_1_13_00_R01
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextConverter
import net.benwoodworth.fastcraft.platform.text.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.Server
import org.bukkit.entity.Player
import java.util.*

class BukkitFcPlayer_1_13_00_R01(
    override val player: Player,
    private val textConverter: BukkitFcTextConverter,
    private val server: Server
) : BukkitFcPlayer {
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
        get() = BukkitFcLocale_1_13_00_R01(player.locale)

    override val isOnline: Boolean
        get() = player.isOnline

    override fun sendMessage(message: FcText) {
        message as BukkitFcText
        when (message) {
            is BukkitFcText.Legacy -> {
                player.sendMessage(message.legacyText)
            }
            is BukkitFcText.Component -> {
                server.dispatchCommand(
                    server.consoleSender,
                    "tellraw $username ${textConverter.toRaw(message)}"
                )
            }
        }
    }

    override fun hasPermission(permission: String): Boolean {
        return player.hasPermission(permission)
    }
}
