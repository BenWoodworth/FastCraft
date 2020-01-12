package net.benwoodworth.fastcraft.crafting.model

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.util.CancellableResult
import javax.inject.Provider

@AutoFactory
class FastCraftRecipe(
    val fastCraftGuiModel: FastCraftGuiModel,
    val preparedRecipe: FcCraftingRecipePrepared,
    var multiplier: Int,
    @Provided private val itemAmountsProvider: Provider<ItemAmounts>,
    @Provided private val itemFactory: FcItemFactory
) {
    fun canCraft(): Boolean {
        val remainingItems = fastCraftGuiModel.inventoryItemAmounts.copy()

        preparedRecipe.ingredientItems.forEach { item ->
            val amountLeft = remainingItems[item]
            val removeAmount = item.amount * multiplier

            when {
                amountLeft < removeAmount -> return false
                else -> remainingItems[item] = amountLeft - removeAmount
            }
        }

        return true
    }

    /**
     * @return `true` iff successful.
     */
    fun craft(dropItems: Boolean): Boolean {
        fastCraftGuiModel.updateInventoryItemAmounts()
        if (!canCraft()) {
            return false
        }

        val craftResult = preparedRecipe.craft()
        val craftedItems = when (craftResult) {
            is CancellableResult.Cancelled -> return false
            is CancellableResult.Result -> craftResult.result
        }

        removeItems(preparedRecipe.ingredientItems, multiplier)

        TODO("Give player crafted items")
        return true
    }

    private fun removeItems(items: List<FcItem>, multiplier: Int) {
        val removeAmounts = itemAmountsProvider.get()
        items.forEach { item ->
            removeAmounts[item] += item.amount * multiplier
        }

        if (removeAmounts.isEmpty()) {
            return
        }

        val removeFromSlots = fastCraftGuiModel.player.inventory.storage.asSequence()
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
}
