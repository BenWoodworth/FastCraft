package net.benwoodworth.fastcraft.dependencies.bukkit.player

import net.benwoodworth.fastcraft.dependencies.abstractions.player.Player
import net.benwoodworth.fastcraft.dependencies.abstractions.player.PlayerProvider
import org.bukkit.Bukkit
import java.util.UUID

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
