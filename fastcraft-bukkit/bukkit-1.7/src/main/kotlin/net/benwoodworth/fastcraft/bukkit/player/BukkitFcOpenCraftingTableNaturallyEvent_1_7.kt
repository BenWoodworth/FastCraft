package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import org.bukkit.event.inventory.InventoryOpenEvent

class FcOpenCraftingTableNaturallyEvent_Bukkit_1_7(
    override val event: InventoryOpenEvent,
    override val player: FcPlayer,
) : FcOpenCraftingTableNaturallyEvent_Bukkit {
    override fun cancel() {
        event.isCancelled = true
    }
}
