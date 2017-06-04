package net.benwoodworth.fastcraft.bukkit.dependencies.event

import net.benwoodworth.fastcraft.bukkit.dependencies.inventory.BukkitItem
import net.benwoodworth.fastcraft.bukkit.dependencies.player.BukkitPlayer
import net.benwoodworth.fastcraft.core.dependencies.event.EventListener
import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventServerStart
import net.benwoodworth.fastcraft.core.dependencies.player.FcPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * Bukkit implementation of `EventListenerRegistry`.
 */
class BukkitEventListenerRegistry : EventListenerRegistry<BukkitItem>, Listener {

    override val serverStart = EventListener<EventServerStart>()

    override val playerJoin = EventListener<EventPlayerJoin<BukkitItem>>()

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        playerJoin.notifyHandlers(object : EventPlayerJoin<BukkitItem> {

            override val player: FcPlayer<BukkitItem>
                get() = BukkitPlayer(event.player)
        })
    }
}
