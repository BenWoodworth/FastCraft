package net.benwoodworth.fastcraft.platform.player

/**
 * Event for when a player opens a crafting inventory by interacting with a crafting table.
 */
interface FcOpenCraftingTableNaturallyEvent {
    val player: FcPlayer

    fun cancel()
}
