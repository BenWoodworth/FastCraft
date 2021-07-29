package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CraftingInventoryViewFactory_1_7 @Inject constructor(
    private val server: Server,
) : CraftingInventoryViewFactory {
    override fun create(
        player: Player,
        inventoryHolder: InventoryHolder?,
        recipe: Recipe?,
    ): InventoryView {
        return CustomInventoryView(
            player = player,
            topInventory = CustomCraftingInventory(player, inventoryHolder, recipe),
        )
    }

    private class CustomInventoryView(
        private val player: Player,
        private val topInventory: Inventory,
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
    }

    private inner class CustomCraftingInventory private constructor(
        private val player: Player,
        private val recipe: Recipe?,
        private val baseInventory: Inventory,
    ) : CraftingInventory, Inventory by baseInventory {
        constructor(player: Player, inventoryHolder: InventoryHolder?, recipe: Recipe?) : this(
            player = player,
            recipe = recipe,
            baseInventory = server.createInventory(inventoryHolder, InventoryType.WORKBENCH)
        )

        private fun air() = ItemStack(Material.AIR)

        override fun getItem(index: Int): ItemStack? {
            return baseInventory.getItem(index) ?: air()
        }

        override fun getContents(): Array<ItemStack> {
            val contents = baseInventory.contents
            for (i in contents.indices) {
                contents[i] = contents[i] ?: air()
            }

            return contents
        }

        override fun getRecipe(): Recipe? {
            return recipe
        }

        override fun getMatrix(): Array<ItemStack?> {
            return Array(size - 1) { slot -> getItem(slot) }
        }

        override fun setMatrix(contents: Array<out ItemStack>) {
            if (contents.size > baseInventory.size - 1) {
                throw IllegalArgumentException("matrix contents too large")
            }

            setContents(contents)
        }

        override fun getResult(): ItemStack? {
            return getItem(9)
        }

        override fun setResult(newResult: ItemStack?) {
            setItem(size - 1, newResult)
        }

        override fun iterator(index: Int): MutableListIterator<ItemStack> {
            val baseIterator = baseInventory.iterator(index)
            return object : MutableListIterator<ItemStack> by baseIterator {
                override fun next(): ItemStack {
                    return baseIterator.next() ?: air()
                }

                override fun previous(): ItemStack {
                    return baseIterator.previous() ?: air()
                }
            }
        }

        override fun iterator(): MutableListIterator<ItemStack> {
            return iterator(0)
        }

        override fun getViewers(): List<HumanEntity> {
            return listOf(player)
        }
    }
}
