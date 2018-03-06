package net.benwoodworth.fastcraft.implementations.bukkit.api.player

import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayerProvider
import org.bukkit.Server
import java.util.*
import javax.inject.Inject

/**
 * Bukkit implementation of [FcPlayerProvider].
 */
class BukkitFcPlayerProvider @Inject constructor(
        private val server: Server
) : FcPlayerProvider {

    override fun getOnlinePlayers(): List<FcPlayer> {
        return server.onlinePlayers
                .map(::BukkitFcPlayer)
    }

    override fun getOnlinePlayer(uuid: UUID): FcPlayer? {
        return server.getPlayer(uuid)
                ?.let(::BukkitFcPlayer)
    }
}
