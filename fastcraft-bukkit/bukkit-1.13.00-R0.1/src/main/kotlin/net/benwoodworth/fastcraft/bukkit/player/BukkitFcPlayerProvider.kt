package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.Bukkit
import java.util.*

class BukkitFcPlayerProvider : FcPlayerProvider {

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
