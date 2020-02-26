package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.Recipe
import javax.inject.Inject

class PrepareCraftInventoryView_1_15_00_R01 private constructor(
    private val player: Player,
    inventoryHolder: InventoryHolder?,
    recipe: Recipe?,
    private val server: Server
) : PrepareCraftInventoryView() {
    open class Factory @Inject constructor(
        private val server: Server
    ) : PrepareCraftInventoryView.Factory {
        override fun create(
            player: Player,
            inventoryHolder: InventoryHolder?,
            recipe: Recipe?
        ): PrepareCraftInventoryView {
            return PrepareCraftInventoryView_1_15_00_R01(
                player = player,
                inventoryHolder = inventoryHolder,
                recipe = recipe,
                server = server
            )
        }
    }

    private val topInventory = PrepareCraftInventoryView.PreparedInventory(
        inventory = server.createInventory(inventoryHolder, InventoryType.WORKBENCH),
        recipe = recipe
    )

    override fun getPlayer(): Player {
        return player
    }

    override fun getType(): InventoryType {
        return topInventory.type
    }

    override fun getBottomInventory(): Inventory {
        return player.inventory
    }

    override fun getTitle(): String {
        return "FastCraft"
    }

    override fun getTopInventory(): PreparedInventory {
        return topInventory
    }
}
