package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.event.player.PlayerJoinEvent

class BukkitFcPlayerJoinEvent_1_7(
    override val event: PlayerJoinEvent,
    private val fcPlayerProvider: FcPlayer.Provider,
) : BukkitFcPlayerJoinEvent {
    override val player: FcPlayer
        get() = fcPlayerProvider.getPlayer(event.player)
}
