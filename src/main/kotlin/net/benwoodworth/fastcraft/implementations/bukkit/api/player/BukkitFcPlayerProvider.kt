package net.benwoodworth.fastcraft.implementations.bukkit.api.player

import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayerProvider
import org.bukkit.Bukkit
import java.util.*

/**
 * Bukkit implementation of [FcPlayerProvider].
 */
class BukkitFcPlayerProvider : FcPlayerProvider {

    override fun getOnlinePlayers(): List<FcPlayer> {
        return Bukkit.getOnlinePlayers()
                .map(::BukkitFcPlayer)
    }

    override fun getOnlinePlayer(uuid: UUID): FcPlayer? {
        return Bukkit.getPlayer(uuid)
                ?.let(::BukkitFcPlayer)
    }
}
