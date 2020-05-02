package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.event.player.PlayerJoinEvent

class BukkitFcPlayerJoinEvent_1_7(
    override val event: PlayerJoinEvent,
    private val playerProvider: FcPlayer.Provider,
) : BukkitFcPlayerJoinEvent {
    override val player: FcPlayer
        get() = playerProvider.getPlayer(event.player)
}
