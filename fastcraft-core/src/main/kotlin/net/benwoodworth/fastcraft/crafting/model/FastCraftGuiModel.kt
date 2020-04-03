package net.benwoodworth.fastcraft.crafting.model

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypeComparator
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.util.CancellableResult
import net.benwoodworth.fastcraft.util.uniqueBy
import javax.inject.Provider

@AutoFactory
class FastCraftGuiModel(
    val player: FcPlayer,
    @Provided private val itemAmountsProvider: Provider<ItemAmounts>,
    @Provided private val craftableRecipeFinder: CraftableRecipeFinder,
    @Provided private val itemFactory: FcItem.Factory,
    @Provided private val itemTypeComparator: FcItemTypeComparator,
) {
    var craftAmount: Int? = null

    val recipes: MutableList<FastCraftRecipe?> = mutableListOf()

    val inventoryItemAmounts: ItemAmounts = itemAmountsProvider.get()

    private val recipeComparator: Comparator<FcCraftingRecipePrepared> =
        compareBy<FcCraftingRecipePrepared, FcItemType>(itemTypeComparator) {
            it.resultsPreview.first().type
        }.thenBy {
            it.resultsPreview.first().amount
        }

    fun updateInventoryItemAmounts() {
        inventoryItemAmounts.clear()

        player.inventory.storage.forEach { slot ->
            slot.item?.let { item -> inventoryItemAmounts += item }
        }
    }

    fun updateCraftAmounts() {
        recipes.forEach { recipe ->
            recipe?.setCraftAmount(craftAmount)
        }
    }

    fun refreshRecipes() {
        updateInventoryItemAmounts()

        recipes.clear()
        craftableRecipeFinder
            .getCraftableRecipes(player, inventoryItemAmounts)
            .uniqueBy { it.ingredients.values.toSet() to it.resultsPreview.toSet() }
            .sortedWith(recipeComparator)
            .map { FastCraftRecipe(this, it) }
            .forEach { recipes += it }
    }


    /**
     * @return `true` iff successful.
     */
    fun craftRecipe(recipeIndex: Int, dropItems: Boolean): Boolean {
        val recipe = recipes[recipeIndex]

        updateInventoryItemAmounts()
        if (recipe?.canCraft() != true) {
            return false
        }

        recipes[recipeIndex] = null // Prevent preparedRecipe.craft() from being crafted twice
        val craftedItems = when (val craftResult = recipe.preparedRecipe.craft()) {
            is CancellableResult.Cancelled -> return false
            is CancellableResult.Result -> craftResult.result
        }

        removeItems(recipe.preparedRecipe.ingredients.values, recipe.multiplier)

        repeat(recipe.multiplier) {
            player.giveItems(craftedItems, dropItems)
        }

        val updatedRecipe = recipe.preparedRecipe.recipe.prepare(player, recipe.preparedRecipe.ingredients)
        recipes[recipeIndex] = when (updatedRecipe) {
            is CancellableResult.Cancelled -> null
            is CancellableResult.Result -> FastCraftRecipe(this, updatedRecipe.result)
        }

        return true
    }

    private fun removeItems(items: Collection<FcItem>, multiplier: Int) {
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

    fun openCraftingTable() {
        player.openCraftingTable()
    }
}
