package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.platform.event.FcEventListener
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent
import net.benwoodworth.fastcraft.util.Extensible

interface FcServer : Extensible {

    val onPlayerJoin: FcEventListener<FcPlayerJoinEvent>
}