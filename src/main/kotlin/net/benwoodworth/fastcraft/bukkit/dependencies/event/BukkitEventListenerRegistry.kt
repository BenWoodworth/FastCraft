package net.benwoodworth.fastcraft.bukkit.dependencies.event

import net.benwoodworth.fastcraft.bukkit.BukkitFastCraft
import net.benwoodworth.fastcraft.bukkit.dependencies.event.events.BukkitEventPlayerJoin
import net.benwoodworth.fastcraft.core.dependencies.event.FcEventListenerRegistry
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginDisable
import net.benwoodworth.fastcraft.core.dependencies.event.events.FcEventPluginEnable
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.server.PluginDisableEvent
import org.bukkit.event.server.PluginEnableEvent
import javax.inject.Inject

/**
 * Bukkit implementation of `FcEventListenerRegistry`.
 */
class BukkitEventListenerRegistry @Inject constructor(
        fastCraft: BukkitFastCraft
) : FcEventListenerRegistry(), Listener {

    init {
        println("Registering events")
        Bukkit.getPluginManager().registerEvents(this, fastCraft)
    }

    @EventHandler
    fun onPluginEnable(event: PluginEnableEvent) {
        pluginEnable.notifyHandlers(object : FcEventPluginEnable {})
    }

    @EventHandler
    fun onPluginDisable(event: PluginDisableEvent) {
        pluginDisable.notifyHandlers(object : FcEventPluginDisable {})
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        playerJoin.notifyHandlers(BukkitEventPlayerJoin(event))
    }
}
