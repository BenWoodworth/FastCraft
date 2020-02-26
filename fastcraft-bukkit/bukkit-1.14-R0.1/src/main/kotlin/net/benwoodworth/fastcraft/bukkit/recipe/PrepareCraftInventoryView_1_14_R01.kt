package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.*
import javax.inject.Inject

class PrepareCraftInventoryView_1_14_R01 private constructor(
    private val player: Player,
    inventoryHolder: InventoryHolder?,
    recipe: Recipe?,
    private val server: Server
) : InventoryView() {
    open class Factory @Inject constructor(
        private val server: Server
    ) : PrepareCraftInventoryView_1_8_R01.Factory(
        server = server
    ){
        override fun create(
            player: Player,
            inventoryHolder: InventoryHolder?,
            recipe: Recipe?
        ): InventoryView {
            return PrepareCraftInventoryView_1_14_R01(
                player = player,
                inventoryHolder = inventoryHolder,
                recipe = recipe,
                server = server
            )
        }
    }

    private val topInventory = PrepareCraftInventoryView_1_8_R01.PreparedInventory(
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

    override fun getTopInventory(): Inventory {
        return topInventory
    }
}
