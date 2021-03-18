package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import org.bukkit.event.player.PlayerJoinEvent

interface FcPlayerJoinEvent_Bukkit : FcPlayerJoinEvent {
    val event: PlayerJoinEvent
}

val FcPlayerJoinEvent.event: PlayerJoinEvent
    get() = (this as FcPlayerJoinEvent_Bukkit).event
