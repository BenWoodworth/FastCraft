package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.Bukkit
import java.util.*

@Suppress("ClassName")
object Bukkit_113_R01_FcPlayerProvider : FcPlayerProvider {

    override fun getOnlinePlayers(): List<FcPlayer> {
        return Bukkit.getOnlinePlayers().map { player ->
            Bukkit_113_R01_FcPlayer(player)
        }
    }

    override fun getPlayer(uuid: UUID): FcPlayer? {
        return Bukkit.getPlayer(uuid)?.let { player ->
            Bukkit_113_R01_FcPlayer(player)
        }
    }
}
