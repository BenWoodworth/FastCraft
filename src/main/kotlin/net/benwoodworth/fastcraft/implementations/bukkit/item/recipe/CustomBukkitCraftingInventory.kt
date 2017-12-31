package net.benwoodworth.fastcraft.implementations.bukkit.item.recipe

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.*

/**
 * Custom implementation of Bukkit's [CraftingInventory].
 *
 * Simulates a [CraftingInventory] shown to a single player.
 */
internal class CustomBukkitCraftingInventory(
        private val player: Player,
        private val recipe: Recipe?
) : CraftingInventory, Inventory by Bukkit.createInventory(
        player,
        InventoryType.WORKBENCH
) {

    /**
     * Get the crafting results given the items in the inventory
     * after the recipe is prepared, and before items are removed
     * from the recipe being crafted.
     *
     * @return the recipe results
     */
    fun getResults(): List<ItemStack> {
        val results = mutableListOf<ItemStack>()
        results.add(getItem(0).clone())
        for (i in 1..9) {
            val item = getItem(i).clone()
            if (item.amount <= 0) {
                continue
            }

            // Filled buckets result in empty buckets (with data stripped)
            if (item.type == Material.WATER_BUCKET
                    || item.type == Material.MILK_BUCKET
                    || item.type == Material.LAVA_BUCKET) {

                results.add(ItemStack(Material.BUCKET, 1))
            }

            if (--item.amount > 0) {
                results.add(item)
            }
        }

        return results.toList()
    }

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
            return this@CustomBukkitCraftingInventory.player
        }

        override fun getType(): InventoryType {
            return this@CustomBukkitCraftingInventory.type
        }

        override fun getBottomInventory(): Inventory {
            return this@CustomBukkitCraftingInventory.player.inventory
        }

        override fun getTopInventory(): Inventory {
            return this@CustomBukkitCraftingInventory
        }
    }
}
