package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.platform.event.FcEvent

interface FcPlayerJoinEvent : FcEvent {

    val player: FcPlayer
}
