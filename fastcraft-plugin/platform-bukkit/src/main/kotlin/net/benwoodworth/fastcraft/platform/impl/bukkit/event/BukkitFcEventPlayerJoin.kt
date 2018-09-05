package net.benwoodworth.fastcraft.platform.impl.bukkit.event

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.event.FcEventPlayerJoin
import net.benwoodworth.fastcraft.platform.impl.bukkit.player.BukkitFcPlayerFactory
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
