package net.benwoodworth.fastcraft.bukkit.dependencies.event.events

import net.benwoodworth.fastcraft.bukkit.dependencies.inventory.BukkitItem
import net.benwoodworth.fastcraft.bukkit.dependencies.player.BukkitPlayer
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.player.FcPlayer
import org.bukkit.event.player.PlayerJoinEvent

/**
 * Created by ben on 6/5/17.
 */
class BukkitEventPlayerJoin(
        private val base: PlayerJoinEvent
) : FcEventPlayerJoin<BukkitItem> {

    override val player: FcPlayer<BukkitItem>
        get() = BukkitPlayer(base.player)
}
