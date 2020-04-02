package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.player.FcPlayerProvider
import org.bukkit.event.Event
import org.bukkit.event.player.PlayerInteractEvent

class BukkitFcPlayerOpenWorkbenchEvent_1_7_5_R01(
    override val event: PlayerInteractEvent,
    private val playerProvider: FcPlayerProvider,
) : BukkitFcPlayerOpenWorkbenchEvent {
    override val player: FcPlayer
        get() = playerProvider.getPlayer(event.player)

    override fun cancel() {
        event.setUseInteractedBlock(Event.Result.DENY)
    }
}


