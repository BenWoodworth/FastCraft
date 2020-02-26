package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.*

abstract class PrepareCraftInventoryView : InventoryView() {
    interface Factory {
        fun create(
            player: Player,
            inventoryHolder: InventoryHolder?,
            recipe: Recipe?
        ): PrepareCraftInventoryView
    }

    abstract override fun getPlayer(): Player

    override fun getType(): InventoryType {
        return topInventory.type
    }

    override fun getBottomInventory(): Inventory {
        return player.inventory
    }

    abstract override fun getTopInventory(): PreparedInventory

    class PreparedInventory(
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
