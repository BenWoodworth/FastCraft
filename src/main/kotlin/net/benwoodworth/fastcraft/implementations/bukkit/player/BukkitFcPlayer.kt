package net.benwoodworth.fastcraft.implementations.bukkit.player

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.dependencies.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.text.BukkitFcText
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.entity.Player
import java.util.*

/**
 * Bukkit implementation of [FcPlayer].
 */
@AutoFactory
class BukkitFcPlayer(
        override val base: Player,

        @Provided private val textHelper: BukkitFcText.Helper
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
        textHelper.sendPlayerMessage(base, message)
    }

    override fun hasPermission(permission: String): Boolean {
        return base.hasPermission(permission)
    }
}
