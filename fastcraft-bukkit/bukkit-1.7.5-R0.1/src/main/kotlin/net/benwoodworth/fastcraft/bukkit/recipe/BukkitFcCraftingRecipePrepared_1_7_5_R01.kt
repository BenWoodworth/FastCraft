package net.benwoodworth.fastcraft.bukkit.recipe

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItem.Factory.Companion.createFcItem
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.util.CancellableResult
import org.bukkit.Server
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.CraftingInventory
import org.bukkit.inventory.InventoryView

@AutoFactory
class BukkitFcCraftingRecipePrepared_1_7_5_R01(
    override val recipe: FcCraftingRecipe,
    override val ingredients: Map<FcIngredient, FcItem>,
    private val ingredientRemnants: List<FcItem>,
    override val resultsPreview: List<FcItem>,
    private val preparedCraftingView: InventoryView,
    @Provided private val itemFactory: FcItem.Factory,
    @Provided private val server: Server,
) : BukkitFcCraftingRecipePrepared {
    private var craftCalled = false

    override fun craft(): CancellableResult<List<FcItem>> {
        require(!craftCalled) { "Only callable once" }
        craftCalled = true

        val craftingInventory = preparedCraftingView.topInventory as CraftingInventory

        val craftEvent = CraftItemEvent(
            craftingInventory.recipe!!,
            preparedCraftingView,
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
            CancellableResult(
                listOf(itemFactory.createFcItem(resultItem!!)) + ingredientRemnants
            )
        }
    }
}
