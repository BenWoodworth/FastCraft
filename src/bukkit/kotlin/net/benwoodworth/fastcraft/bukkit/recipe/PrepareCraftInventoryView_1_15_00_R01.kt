package net.benwoodworth.fastcraft.bukkit.recipe

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import org.bukkit.Server
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.*

@AutoFactory
class PrepareCraftInventoryView_1_15_00_R01(
    private val player: Player,
    inventoryHolder: InventoryHolder?,
    recipe: Recipe?,
    @Provided private val server: Server
) : InventoryView() {
    private val topInventory = PreparedCraftingInventory(
        inventory = server.createInventory(inventoryHolder, InventoryType.WORKBENCH),
        recipe = recipe
    )

    override fun getPlayer(): HumanEntity {
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

    override fun getTopInventory(): CraftingInventory {
        return topInventory
    }

    private class PreparedCraftingInventory(
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
