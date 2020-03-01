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
    ) : PrepareCraftInventoryView_1_7_5_R01.Factory(
        server = server
    ) {
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

    private val topInventory = PreparedInventory(
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
        return "Crafting"
    }

    override fun getTopInventory(): Inventory {
        return topInventory
    }

    private class PreparedInventory(
        private val inventory: Inventory,
        private val recipe: Recipe?
    ) : CraftingInventory, Inventory by inventory {
        override fun getMatrix(): Array<ItemStack?> {
            return Array(size - 1) { slot -> getItem(slot) }
        }

        override fun setResult(newResult: ItemStack?) {
            setItem(size - 1, newResult)
        }

        override fun getRecipe(): Recipe? {
            return recipe
        }

        override fun getResult(): ItemStack? {
            return getItem(9)
        }

        override fun setMatrix(contents: Array<out ItemStack>) {
            contents.forEachIndexed { i, item ->
                setItem(i, item)
            }
        }
    }
}
