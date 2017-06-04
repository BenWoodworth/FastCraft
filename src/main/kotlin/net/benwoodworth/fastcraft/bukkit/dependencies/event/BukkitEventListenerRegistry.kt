package net.benwoodworth.fastcraft.bukkit.dependencies.event

import net.benwoodworth.fastcraft.bukkit.BukkitFastCraft
import net.benwoodworth.fastcraft.bukkit.dependencies.inventory.BukkitItem
import net.benwoodworth.fastcraft.bukkit.dependencies.player.BukkitPlayer
import net.benwoodworth.fastcraft.core.dependencies.event.EventListener
import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.EventPluginEnable
import net.benwoodworth.fastcraft.core.dependencies.player.FcPlayer
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.event.server.PluginEnableEvent
import javax.inject.Inject

/**
 * Bukkit implementation of `EventListenerRegistry`.
 */
class BukkitEventListenerRegistry @Inject constructor(
        fastCraft: BukkitFastCraft
) : EventListenerRegistry<BukkitItem>, Listener {

    init {
        println("Registering events")
        Bukkit.getPluginManager().registerEvents(this, fastCraft)
    }

    override val pluginEnable = EventListener<EventPluginEnable>()
    override val pluginDisable = EventListener<EventPluginDisable>()
    override val playerJoin = EventListener<EventPlayerJoin<BukkitItem>>()

    @EventHandler
    fun onPluginEnable(event: PluginEnableEvent) {
        pluginEnable.notifyHandlers(object : EventPluginEnable {})
    }

    @EventHandler
    fun onPluginDisable(event: PluginDisableEvent) {
        pluginDisable.notifyHandlers(object : EventPluginDisable {})
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        playerJoin.notifyHandlers(object : EventPlayerJoin<BukkitItem> {

            override val player: FcPlayer<BukkitItem>
                get() = BukkitPlayer(event.player)
        })
    }
}
