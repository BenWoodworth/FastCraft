package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.bukkit.locale.BukkitFcLocale_1_13_00_R01
import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcLegacyTextFactory
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.entity.Player
import java.util.*

class BukkitFcPlayer_1_13_00_R01(
    override val player: Player,
    private val legacyTextFactory: FcLegacyTextFactory
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
        with(legacyTextFactory.bukkit) {
            // TODO Send raw text
            player.sendMessage(FcLegacyText(message, locale).bukkit.legacyText)
        }
    }

    override fun hasPermission(permission: String): Boolean {
        return player.hasPermission(permission)
    }
}
