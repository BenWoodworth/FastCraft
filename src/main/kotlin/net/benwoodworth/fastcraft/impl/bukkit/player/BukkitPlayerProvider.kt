package net.benwoodworth.fastcraft.impl.bukkit.player

import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.dependencies.player.PlayerProvider
import org.bukkit.Bukkit
import java.util.*

/**
 * Bukkit implementation of [PlayerProvider].
 */
class BukkitPlayerProvider : PlayerProvider {

    override fun getOnlinePlayers(): List<Player> {
        return Bukkit.getOnlinePlayers()
                .map(::BukkitPlayer)
    }

    override fun getOnlinePlayer(uuid: UUID): Player? {
        return Bukkit.getPlayer(uuid)
                ?.let(::BukkitPlayer)
    }
}
