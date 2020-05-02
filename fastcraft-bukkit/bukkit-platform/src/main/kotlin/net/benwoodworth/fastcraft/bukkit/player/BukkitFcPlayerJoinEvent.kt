package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import org.bukkit.event.player.PlayerJoinEvent

interface BukkitFcPlayerJoinEvent : FcPlayerJoinEvent {
    val event: PlayerJoinEvent
}

val FcPlayerJoinEvent.event: PlayerJoinEvent
    get() = (this as BukkitFcPlayerJoinEvent).event
