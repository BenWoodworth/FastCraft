package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextConverter
import net.benwoodworth.fastcraft.platform.server.FcPlayer
import org.bukkit.Server
import org.bukkit.entity.Player
import java.util.*
import javax.inject.Inject

class BukkitFcPlayerProvider_1_13_00_R01 @Inject constructor(
    private val textConverter: BukkitFcTextConverter,
    private val server: Server
) : BukkitFcPlayerProvider {

    override fun getOnlinePlayers(): List<FcPlayer> {
        return server.onlinePlayers.map { player ->
            BukkitFcPlayer_1_13_00_R01(player, textConverter, server)
        }
    }

    override fun getPlayer(uuid: UUID): FcPlayer? {
        return server.getPlayer(uuid)?.let { player ->
            BukkitFcPlayer_1_13_00_R01(player, textConverter, server)
        }
    }

    override fun getPlayer(player: Player): FcPlayer {
        return BukkitFcPlayer_1_13_00_R01(player, textConverter, server)
    }
}
