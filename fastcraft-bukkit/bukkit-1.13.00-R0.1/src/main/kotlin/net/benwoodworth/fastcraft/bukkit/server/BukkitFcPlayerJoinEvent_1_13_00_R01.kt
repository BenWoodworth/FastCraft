package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.bukkit.player.BukkitFcPlayerJoinEvent
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.event.player.PlayerJoinEvent

class BukkitFcPlayerJoinEvent_1_13_00_R01(
    override val event: PlayerJoinEvent,
    private val playerProvider: FcPlayerProvider
) : BukkitFcPlayerJoinEvent {

    override val player: FcPlayer
        get() = playerProvider.bukkit.getPlayer(event.player)
}
