package net.benwoodworth.fastcraft.platform.bukkit.player

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.entity.Player
import java.util.*

/**
 * Bukkit implementation of [FcPlayer].
 */
@AutoFactory
class BukkitFcPlayer(
        override val base: Player,

        @Provided private val textConverter: BukkitFcText.Converter
) : FcPlayer, Adapter<Player>() {

    override val username: String
        get() = base.name

    override var customName: String?
        get() = base.displayName
        set(value) {
            base.displayName = value
        }

    override val uuid: UUID
        get() = base.uniqueId

    override fun sendMessage(message: FcText) {
        base.sendMessage(textConverter.toLegacyText(message as BukkitFcText))
    }

    override fun hasPermission(permission: String): Boolean {
        return base.hasPermission(permission)
    }
}
