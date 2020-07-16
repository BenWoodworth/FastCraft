package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import kotlin.math.ceil

class FastCraftRecipe(
    private val fastCraftGuiModel: FastCraftGuiModel,
    val preparedRecipe: FcCraftingRecipePrepared,
    private val tcItemStack: FcItemStack.TypeClass,
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

        val baseAmount = tcItemStack.run { preparedRecipe.resultsPreview.first().amount }
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

        preparedRecipe.ingredients.values.forEach { itemStack ->
            val amountLeft = remainingItems[itemStack]
            val removeAmount = tcItemStack.run { itemStack.amount } * multiplier

            when {
                amountLeft < removeAmount -> return false
                else -> remainingItems[itemStack] = amountLeft - removeAmount
            }
        }

        return true
    }
}
