package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.Bukkit
import java.util.*

@Suppress("ClassName")
object `BukkitFcPlayerProvider$1_13_R0_1` : FcPlayerProvider {

    override fun getOnlinePlayers(): List<FcPlayer> {
        return Bukkit.getOnlinePlayers().map { player ->
            `BukkitFcPlayer$1_13_R0_1`(player)
        }
    }

    override fun getPlayer(uuid: UUID): FcPlayer? {
        return Bukkit.getPlayer(uuid)?.let { player ->
            `BukkitFcPlayer$1_13_R0_1`(player)
        }
    }
}
