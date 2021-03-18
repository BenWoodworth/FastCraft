package net.benwoodworth.fastcraft.bukkit.player

import net.benwoodworth.fastcraft.platform.player.FcOpenCraftingTableNaturallyEvent
import org.bukkit.event.inventory.InventoryOpenEvent

interface FcOpenCraftingTableNaturallyEvent_Bukkit : FcOpenCraftingTableNaturallyEvent {
    val event: InventoryOpenEvent
}

val FcOpenCraftingTableNaturallyEvent.event: InventoryOpenEvent
    get() = (this as FcOpenCraftingTableNaturallyEvent_Bukkit).event
