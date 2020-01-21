package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.events.HandlerSet

interface FcPlayerEvents {
    val onPlayerJoin: HandlerSet<FcPlayerJoinEvent>
}
