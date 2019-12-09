package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.events.HandlerSet
import net.benwoodworth.fastcraft.platform.player.FcPlayerJoinEvent

interface FcServer {
    val onPlayerJoin: HandlerSet<FcPlayerJoinEvent>
}
