package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.*
import javax.inject.Inject

class CraftingInventoryViewFactory_1_14_R01 @Inject constructor(
    private val server: Server
) : CraftingInventoryViewFactory {
    override fun create(
        player: Player,
        inventoryHolder: InventoryHolder?,
        recipe: Recipe?
    ): InventoryView {
        return CustomInventoryView(
            player = player,
            topInventory = CustomCraftingInventory(inventoryHolder, recipe)
        )
    }

    private inner class CustomInventoryView(
        private val player: Player,
        private val topInventory: Inventory
    ) : InventoryView() {
        override fun getPlayer(): Player {
            return player
        }

        override fun getType(): InventoryType {
            return topInventory.type
        }

        override fun getBottomInventory(): Inventory {
            return player.inventory
        }

        override fun getTopInventory(): Inventory {
            return topInventory
        }

        override fun getTitle(): String {
            return "Crafting"
        }
    }

    private inner class CustomCraftingInventory(
        private val inventoryHolder: InventoryHolder?,
        private val recipe: Recipe?
    ) : CraftingInventory,
        Inventory by server.createInventory(inventoryHolder, InventoryType.WORKBENCH) {

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