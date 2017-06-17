//package net.benwoodworth.fastcraft.bukkit.dependencies.event
//
//import net.benwoodworth.fastcraft.bukkit.BukkitFastCraft
//import net.benwoodworth.fastcraft.bukkit.dependencies.event.events.BukkitEventPlayerJoin
//import net.benwoodworth.fastcraft.core.dependencies.event.EventListenerRegistry
//import net.benwoodworth.fastcraft.core.dependencies.event.events.PluginDisableEventAdapter
//import net.benwoodworth.fastcraft.core.dependencies.event.events.PluginEnableEventAdapter
//import org.bukkit.Bukkit
//import org.bukkit.event.EventHandler
//import org.bukkit.event.Listener
//import org.bukkit.event.player.PlayerJoinEvent
//import org.bukkit.event.server.PluginDisableEvent
//import org.bukkit.event.server.PluginEnableEvent
//import javax.inject.Inject
//
///**
// * Bukkit implementation of `EventListenerRegistry`.
// */
//class BukkitEventListenerRegistry @Inject constructor(
//        fastCraft: BukkitFastCraft
//) : EventListenerRegistry(), Listener {
//
//    init {
//        println("Registering events")
//        Bukkit.getPluginManager().registerEvents(this, fastCraft)
//    }
//
//    @EventHandler
//    fun onPluginEnable(event: PluginEnableEvent) {
//        pluginEnable.notifyHandlers(object : PluginEnableEventAdapter {})
//    }
//
//    @EventHandler
//    fun onPluginDisable(event: PluginDisableEvent) {
//        pluginDisable.notifyHandlers(object : PluginDisableEventAdapter {})
//    }
//
//    @EventHandler
//    fun onPlayerJoin(event: PlayerJoinEvent) {
//        playerJoin.notifyHandlers(BukkitEventPlayerJoin(event))
//    }
//}
