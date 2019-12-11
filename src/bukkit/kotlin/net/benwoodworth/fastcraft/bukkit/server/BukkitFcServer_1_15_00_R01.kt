package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.bukkit.BukkitFastCraftPlugin
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerJoinEvent_1_15_00_R01
import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.PluginManager
import javax.inject.Inject

class BukkitFcServer_1_15_00_R01 @Inject constructor(
    plugin: BukkitFastCraftPlugin,
    private val playerProvider: FcPlayerProvider,
    private val pluginManager: PluginManager
) : BukkitFcServer {
    override val onPlayerJoin: HandlerSet<FcPlayerJoinEvent> = HandlerSet()

    init {
        pluginManager.registerEvents(ServerListener(), plugin)
    }

    private inner class ServerListener : Listener {
        @EventHandler
        fun onPlayerJoin(event: PlayerJoinEvent) {
            onPlayerJoin.notifyHandlers(
                BukkitFcPlayerJoinEvent_1_15_00_R01(
                    event,
                    playerProvider
                )
            )
        }
    }
}
