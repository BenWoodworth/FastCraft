package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.platform.event.FcEvent
import net.benwoodworth.fastcraft.util.Extensible

interface FcPlayerJoinEvent : Extensible, FcEvent {

    val player: FcPlayer
}
