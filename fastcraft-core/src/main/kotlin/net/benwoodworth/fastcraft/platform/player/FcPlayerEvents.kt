package net.benwoodworth.fastcraft.platform.player

import net.benwoodworth.fastcraft.events.HandlerSet

interface FcPlayerEvents {
    val onPlayerJoin: HandlerSet<FcPlayerJoinEvent>

    /**
     * Monitors when players interact with a crafting table block.
     */
    val onOpenCraftingTableNaturally: HandlerSet<FcOpenCraftingTableNaturallyEvent>

    val onPlayerInventoryChange: HandlerSet<FcPlayerInventoryChangeEvent>
}
