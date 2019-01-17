package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.Bukkit
import java.util.*

class BukkitFcPlayerProvider : BukkitFcPlayerProvider {

    override fun getOnlinePlayers(): List<FcPlayer> {
        return Bukkit.getOnlinePlayers().map { player ->
            BukkitFcPlayer(player)
        }
    }

    override fun getPlayer(uuid: UUID): FcPlayer? {
        return Bukkit.getPlayer(uuid)?.let { player ->
            BukkitFcPlayer(player)
        }
    }
}
