package net.benwoodworth.fastcraft.crafting.model

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcRecipeService
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import net.benwoodworth.fastcraft.util.CancellableResult
import javax.inject.Provider

@AutoFactory
class FastCraftGuiModel(
    private val player: FcPlayer,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val recipeService: FcRecipeService,
    @Provided private val itemAmountsProvider: Provider<ItemAmounts>,
    @Provided private val itemFactory: FcItemFactory
) {
    var recipes: List<FastCraftRecipe> = emptyList()

    val inventoryItemAmounts: ItemAmounts = itemAmountsProvider.get()

    fun updateInventoryItemAmounts() {
        inventoryItemAmounts.clear()

        player.inventory.storage.forEach { slot ->
            slot.item?.let { item -> inventoryItemAmounts += item }
        }
    }

    fun canCraftRecipe(recipe: FastCraftRecipe): Boolean {
        val remainingItems = inventoryItemAmounts.copy()

        recipe.preparedRecipe.ingredientItems.forEach { item ->
            val amountLeft = remainingItems[item]
            val removeAmount = item.amount * recipe.multiplier

            when {
                amountLeft < removeAmount -> return false
                else -> remainingItems[item] = amountLeft - removeAmount
            }
        }

        return true
    }

    fun removeItems(items: List<FcItem>, multiplier: Int) {
        val removeAmounts = itemAmountsProvider.get()
        items.forEach { item ->
            removeAmounts[item] += item.amount * multiplier
        }

        if (removeAmounts.isEmpty()) {
            return
        }

        val removeFromSlots = player.inventory.storage.asSequence()
            .filter { it.item != null && it.item!!.amount > 0 }
            .sortedBy { it.item!!.amount }

        for (slot in removeFromSlots) {
            val item = slot.item!!
            val removeAmount = removeAmounts[item]

            when {
                item.amount <= 0 -> Unit
                removeAmount <= 0 -> Unit
                removeAmount >= item.amount -> {
                    removeAmounts[item] = removeAmount - item.amount
                    slot.item = null
                }
                removeAmount < item.amount -> {
                    removeAmounts[item] = 0
                    slot.item = itemFactory.copyItem(
                        item = item,
                        amount = item.amount - removeAmount
                    )
                }
                else -> throw IllegalStateException()
            }
        }
    }

    /**
     * @return `true` iff successful.
     */
    fun craftRecipe(recipe: FastCraftRecipe, dropItems: Boolean): Boolean {
        updateInventoryItemAmounts()
        if (!canCraftRecipe(recipe)) {
            return false
        }

        val craftResult = recipe.preparedRecipe.craft()
        val craftedItems = when (craftResult) {
            is CancellableResult.Cancelled -> return false
            is CancellableResult.Result -> craftResult.result
        }

        removeItems(recipe.preparedRecipe.ingredientItems, recipe.multiplier)

        TODO("Give player crafted items")
        return true
    }
}
