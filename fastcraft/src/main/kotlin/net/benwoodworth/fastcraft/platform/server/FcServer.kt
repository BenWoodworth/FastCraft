package net.benwoodworth.fastcraft.platform.server

import net.benwoodworth.fastcraft.events.HandlerSet

interface FcServer {

    val onPlayerJoin: HandlerSet<FcPlayerJoinEvent>
}