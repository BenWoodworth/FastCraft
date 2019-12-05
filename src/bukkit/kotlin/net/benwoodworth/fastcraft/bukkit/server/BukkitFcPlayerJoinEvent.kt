package net.benwoodworth.fastcraft.bukkit.server

import net.benwoodworth.fastcraft.platform.server.FcPlayerJoinEvent
import org.bukkit.event.player.PlayerJoinEvent

interface BukkitFcPlayerJoinEvent : FcPlayerJoinEvent {
    val event: PlayerJoinEvent
}

val FcPlayerJoinEvent.event: PlayerJoinEvent
    get() = (this as BukkitFcPlayerJoinEvent).event
