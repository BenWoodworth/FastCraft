package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.CraftingInventory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView

class PrepareCraftInventoryView_1_15_00_R01(
    private val player: Player,
    private val inventory: CraftingInventory
) : InventoryView() {

    override fun getPlayer(): HumanEntity {
        return player
    }

    override fun getType(): InventoryType {
        return inventory.type
    }

    override fun getBottomInventory(): Inventory {
        return player.inventory
    }

    override fun getTitle(): String {
        return "TODO" // TODO
    }

    override fun getTopInventory(): Inventory {
        return inventory
    }
}
