package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.event.Event
import org.bukkit.event.player.PlayerInteractEvent

class BukkitFcPlayerOpenWorkbenchEvent_1_7(
    override val event: PlayerInteractEvent,
    private val playerProvider: FcPlayer.Provider,
) : BukkitFcPlayerOpenWorkbenchEvent {
    override val player: FcPlayer
        get() = playerProvider.getPlayer(event.player)

    override fun cancel() {
        event.setUseInteractedBlock(Event.Result.DENY)
    }
}


