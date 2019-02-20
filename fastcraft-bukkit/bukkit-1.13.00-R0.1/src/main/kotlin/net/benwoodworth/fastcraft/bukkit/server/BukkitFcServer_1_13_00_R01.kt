package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.bukkit.text.BukkitFcRawTextFactory
import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.server.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.server.FcPlayerProvider
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import javax.inject.Inject

class BukkitFcServer_1_13_00_R01 @Inject constructor(
    plugin: Plugin,
    private val rawTextFactory: BukkitFcRawTextFactory,
    private val playerProvider: FcPlayerProvider
) : BukkitFcServer {

    override val onPlayerJoin: HandlerSet<FcPlayerJoinEvent> = HandlerSet()

    init {
        Bukkit.getPluginManager().registerEvents(ServerListener(), plugin)
    }

    private inner class ServerListener : Listener {

        @EventHandler
        fun onPlayerJoin(event: PlayerJoinEvent) {
            onPlayerJoin.notifyHandlers(BukkitFcPlayerJoinEvent_1_13_00_R01(event, playerProvider))
        }
    }
}