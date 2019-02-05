package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.bukkit.event.BukkitFcEventListener_1_13_00_R01
import net.benwoodworth.fastcraft.platform.event.FcEventListener
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import javax.inject.Inject

class BukkitFcServerListeners_1_13_00_R01 @Inject constructor(
    plugin: Plugin,
    playerProvider: FcPlayerProvider
) : BukkitFcServerListeners {

    override val onPlayerJoin: FcEventListener<FcPlayerJoinEvent> =
        BukkitFcEventListener_1_13_00_R01.create(plugin) { event: PlayerJoinEvent ->
            BukkitFcPlayerJoinEvent_1_13_00_R01(event, playerProvider)
        }
}