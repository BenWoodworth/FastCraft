package net.benwoodworth.fastcraft.implementations.bukkit.event

import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.event.FcEventPlayerJoin
import net.benwoodworth.fastcraft.implementations.bukkit.api.player.BukkitFcPlayer
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.event.player.PlayerJoinEvent

/**
 * Bukkit implementation of [FcEventPlayerJoin].
 */
class BukkitFcEventPlayerJoin(
        baseEvent: PlayerJoinEvent
) : FcEventPlayerJoin, Adapter<PlayerJoinEvent>(baseEvent) {

    override val player: FcPlayer
        get() = BukkitFcPlayer(base.player)
}
