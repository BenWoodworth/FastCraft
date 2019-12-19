package net.benwoodworth.fastcraft.bukkit.recipe

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.createFcItem
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.util.CancellableResult
import org.bukkit.Server
import org.bukkit.event.inventory.*
import org.bukkit.inventory.CraftingInventory
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

@AutoFactory
class BukkitFcCraftingRecipePrepared_1_15_00_R01(
    override val recipe: BukkitFcCraftingRecipe_1_15_00_R01,
    private val craftingInventory: CraftingInventory,
    private val craftingView: InventoryView,
    @Provided private val itemFactory: FcItemFactory,
    @Provided private val productProvider: IngredientRemnantProvider,
    @Provided private val server: Server
) : BukkitFcCraftingRecipePrepared {
    private var craftPreviewCalled: Boolean = false
    private var craftCalled: Boolean = false

    override val ingredients: List<FcItem> = run {
        val ingredients = mutableMapOf<ItemStack, Int>()

        for (item in craftingInventory.matrix) {
            if (item == null || item.amount < 1) continue

            val key = item.clone()
            key.amount = 1

            ingredients[key] = ingredients.getOrDefault(key, 0) + 1
        }

        ingredients.map { (item, amount) ->
            item.amount = amount
            itemFactory.createFcItem(item)
        }
    }

    override fun craftPreview(): CancellableResult<List<FcItem>> {
        require(!craftPreviewCalled) { "Only callable once." }
        craftPreviewCalled = true

        val prepareEvent = PrepareItemCraftEvent(craftingInventory, craftingView, false)
        server.pluginManager.callEvent(prepareEvent)

        val resultItem = craftingInventory.result
        val isCancelled = resultItem == null || resultItem.amount < 1

        return if (isCancelled) {
            CancellableResult.Cancelled
        } else {
            // TODO Remnants
            CancellableResult.Result(
                listOf(itemFactory.createFcItem(resultItem!!))
            )
        }
    }

    override fun craft(): CancellableResult<List<FcItem>> {
        require(!craftCalled) { "Only callable once." }
        craftCalled = true

        val craftEvent = CraftItemEvent(
            recipe.recipe,
            craftingView,
            InventoryType.SlotType.RESULT,
            9,
            ClickType.SHIFT_LEFT,
            InventoryAction.MOVE_TO_OTHER_INVENTORY
        )

        server.pluginManager.callEvent(craftEvent)
        val resultItem = craftingInventory.result
        val isCancelled = craftEvent.isCancelled || resultItem == null || resultItem.amount < 1

        return if (isCancelled) {
            CancellableResult.Cancelled
        } else {
            // TODO Remnants
            CancellableResult.Result(
                listOf(itemFactory.createFcItem(resultItem!!))
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}
