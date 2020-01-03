package net.benwoodworth.fastcraft.bukkit.recipe

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.createFcItem
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import org.bukkit.Server
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryType

@AutoFactory
class BukkitFcCraftingRecipePrepared_1_15_00_R01(
    override val recipe: FcCraftingRecipe,
    private val ingredientRemnants: List<FcItem>,
    override val resultsPreview: List<FcItem>,
    private val preparedCraftingView: PrepareCraftInventoryView_1_15_00_R01,
    @Provided private val itemFactory: FcItemFactory,
    @Provided private val server: Server
) : BukkitFcCraftingRecipePrepared {
    private var craftCalled = false

    override fun craft(): FcCraftingRecipePrepared.CraftResult {
        require(!craftCalled) { "Only callable once" }
        craftCalled = true

        val craftEvent = CraftItemEvent(
            preparedCraftingView.topInventory.recipe!!,
            preparedCraftingView,
            InventoryType.SlotType.RESULT,
            9,
            ClickType.SHIFT_LEFT,
            InventoryAction.MOVE_TO_OTHER_INVENTORY
        )

        server.pluginManager.callEvent(craftEvent)

        val resultItem = preparedCraftingView.topInventory.result
        val isCancelled = craftEvent.isCancelled || resultItem == null || resultItem.amount < 1

        return if (isCancelled) {
            FcCraftingRecipePrepared.CraftResult(emptyList(), true)
        } else {
            FcCraftingRecipePrepared.CraftResult(
                results = listOf(itemFactory.createFcItem(resultItem!!)) + ingredientRemnants,
                cancelled = false
            )
        }
    }
}
