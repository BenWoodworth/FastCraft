package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayerOpenWorkbenchEvent
import org.bukkit.event.player.PlayerInteractEvent

interface BukkitFcPlayerOpenWorkbenchEvent : FcPlayerOpenWorkbenchEvent {
    val event: PlayerInteractEvent

    companion object {
        val FcPlayerOpenWorkbenchEvent.event: PlayerInteractEvent
            get() = (this as BukkitFcPlayerOpenWorkbenchEvent).event
    }
}
