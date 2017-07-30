package net.benwoodworth.fastcraft.impl.bukkit.item.recipe

import org.bukkit.Bukkit
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.CraftingInventory
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe

/**
 * Custom implementation of Bukkit's [CraftingInventory].
 *
 * Simulates a [CraftingInventory] shown to a single player.
 */
internal class CustomCraftingInventory(
        private val player: Player,
        private val recipe: Recipe?
) : CraftingInventory, Inventory by Bukkit.createInventory(
        player,
        InventoryType.WORKBENCH
) {

    override fun getMatrix(): Array<ItemStack> {
        return Array(9) { getItem(it + 1) }
    }

    override fun setResult(newResult: ItemStack?) {
        return setItem(0, newResult)
    }

    override fun getRecipe(): Recipe? {
        return recipe
    }

    override fun getResult(): ItemStack? {
        return getItem(0)
    }

    override fun setMatrix(contents: Array<out ItemStack>) {
        for (i in 0..8) {
            setItem(i + 1, contents[i])
        }
    }

    override fun getViewers(): MutableList<HumanEntity> {
        return mutableListOf(player)
    }

    inner class View : InventoryView() {

        override fun getPlayer(): HumanEntity {
            return this@CustomCraftingInventory.player
        }

        override fun getType(): InventoryType {
            return this@CustomCraftingInventory.type
        }

        override fun getBottomInventory(): Inventory {
            return this@CustomCraftingInventory.player.inventory
        }

        override fun getTopInventory(): Inventory {
            return this@CustomCraftingInventory
        }
    }
}
