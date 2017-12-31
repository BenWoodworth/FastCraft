package net.benwoodworth.fastcraft.implementations.bukkit.event

import net.benwoodworth.fastcraft.dependencies.event.EventPlayerJoin
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.implementations.bukkit.player.BukkitPlayer
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.event.player.PlayerJoinEvent

/**
 * Bukkit implementation of [EventPlayerJoin].
 */
class BukkitEventPlayerJoin(
        baseEvent: PlayerJoinEvent
) : EventPlayerJoin, Adapter<PlayerJoinEvent>(baseEvent) {

    override val player: Player
        get() = BukkitPlayer(base.player)
}
