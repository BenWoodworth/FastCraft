package net.benwoodworth.fastcraft.implementations.bukkit.event

import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.event.FcEventPlayerJoin
import net.benwoodworth.fastcraft.implementations.bukkit.api.player.BukkitFcPlayerFactory
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.event.player.PlayerJoinEvent

/**
 * Bukkit implementation of [FcEventPlayerJoin].
 */
class BukkitFcEventPlayerJoin(
        override val base: PlayerJoinEvent,
        private val playerFactory: BukkitFcPlayerFactory
) : FcEventPlayerJoin, Adapter<PlayerJoinEvent>() {

    override val player: FcPlayer
        get() = playerFactory.create(base.player)
}
