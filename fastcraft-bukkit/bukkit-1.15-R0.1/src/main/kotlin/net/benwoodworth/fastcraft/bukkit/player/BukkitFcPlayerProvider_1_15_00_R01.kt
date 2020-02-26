package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.Server
import org.bukkit.entity.Player
import java.util.*
import javax.inject.Inject

class BukkitFcPlayerProvider_1_15_00_R01 @Inject constructor(
    private val server: Server,
    private val playerFactory: BukkitFcPlayer_1_15_00_R01Factory
) : BukkitFcPlayerProvider {
    override fun getOnlinePlayers(): List<FcPlayer> {
        return server.onlinePlayers.map { player ->
            playerFactory.create(player)
        }
    }

    override fun getPlayer(uuid: UUID): FcPlayer? {
        return server.getPlayer(uuid)?.let { player ->
            playerFactory.create(player)
        }
    }

    override fun getPlayer(player: Player): FcPlayer {
        return playerFactory.create(player)
    }
}
