package net.benwoodworth.fastcraft.implementations.bukkit.api.player

import net.benwoodworth.fastcraft.dependencies.api.player.Player
import net.benwoodworth.fastcraft.dependencies.api.player.PlayerProvider
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
