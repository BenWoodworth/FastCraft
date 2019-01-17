package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.Bukkit
import java.util.*

class BukkitFcPlayerProvider_1_13_00_R01 : BukkitFcPlayerProvider {

    override fun getOnlinePlayers(): List<FcPlayer> {
        return Bukkit.getOnlinePlayers().map { player ->
            BukkitFcPlayer_1_13_00_R01(player)
        }
    }

    override fun getPlayer(uuid: UUID): FcPlayer? {
        return Bukkit.getPlayer(uuid)?.let { player ->
            BukkitFcPlayer_1_13_00_R01(player)
        }
    }
}
