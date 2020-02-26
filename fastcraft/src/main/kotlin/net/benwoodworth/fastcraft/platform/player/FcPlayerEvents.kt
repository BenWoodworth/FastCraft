package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.events.HandlerSet

interface FcPlayerEvents {
    val onPlayerJoin: HandlerSet<FcPlayerJoinEvent>

    /**
     * Late priority event for when a player opens a workbench block.
     */
    val onPlayerOpenWorkbench: HandlerSet<FcPlayerOpenWorkbenchEvent>
}
