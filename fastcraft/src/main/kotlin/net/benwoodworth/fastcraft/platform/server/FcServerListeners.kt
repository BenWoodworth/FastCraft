package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.platform.event.FcEventListener
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent

interface FcServerListeners {

    val onPlayerJoin: FcEventListener<FcPlayerJoinEvent>
}