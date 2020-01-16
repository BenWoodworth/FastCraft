package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import kotlin.math.ceil

class FastCraftRecipe(
    val fastCraftGuiModel: FastCraftGuiModel,
    val preparedRecipe: FcCraftingRecipePrepared
) {
    var multiplier: Int = 1
        private set

    init {
        setCraftAmount(fastCraftGuiModel.craftAmount)
    }

    fun setCraftAmount(amount: Int?) {
        if (amount == null) {
            multiplier = 1
            return
        }

        val baseAmount = preparedRecipe.resultsPreview.first().amount
        var newMultiplier = ceil(amount.toDouble() / baseAmount).toInt()

        if (baseAmount * newMultiplier > 64) {
            newMultiplier = amount / baseAmount
        }

        if (newMultiplier < 1) {
            newMultiplier = 1
        }

        multiplier = newMultiplier
    }

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
}
