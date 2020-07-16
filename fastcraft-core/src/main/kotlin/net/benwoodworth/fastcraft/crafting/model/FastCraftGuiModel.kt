package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import net.benwoodworth.fastcraft.util.CancellableResult
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

class FastCraftGuiModel(
    val player: FcPlayer,
    private val itemAmountsProvider: Provider<ItemAmounts>,
    private val craftableRecipeFinder: CraftableRecipeFinder,
    private val itemStackFactory: FcItemStack.Factory,
    private val fcPlayerTypeClass: FcPlayer.TypeClass,
    private val fcItemStackTypeClass: FcItemStack.TypeClass,
) {
    var craftAmount: Int? = null
    val recipes: MutableList<FastCraftRecipe?> = mutableListOf()

    val inventoryItemAmounts: ItemAmounts = itemAmountsProvider.get()
    var listener: Listener? = null

    private val craftableRecipeFinderListener = CraftableRecipeFinderListener()

    fun updateInventoryItemAmounts() {
        inventoryItemAmounts.clear()

        fcPlayerTypeClass.run { player.inventory }.storage.forEach { slot ->
            slot.itemStack?.let { itemStack -> inventoryItemAmounts += itemStack }
        }
    }

    fun updateCraftAmounts() {
        recipes.forEach { recipe ->
            recipe?.setCraftAmount(craftAmount)
        }
    }

    fun refreshRecipes() {
        updateInventoryItemAmounts()

        craftableRecipeFinder.cancel(player)
        recipes.clear()
        craftableRecipeFinder.loadRecipes(player, craftableRecipeFinderListener)
    }

    private inner class CraftableRecipeFinderListener : CraftableRecipeFinder.Listener {
        override fun onNewRecipesLoaded(newRecipes: List<FcCraftingRecipePrepared>) {
            newRecipes
//                .uniqueBy { it.ingredients.values.toSet() to it.resultsPreview.toSet() }
                .map { FastCraftRecipe(this@FastCraftGuiModel, it, fcItemStackTypeClass) }
                .forEach { recipes += it }

            listener?.onRecipesChange(recipes)
        }
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
            fcPlayerTypeClass.run { player.giveItems(craftedItems, dropItems) }
        }

        val updatedRecipe = recipe.preparedRecipe.recipe.prepare(player, recipe.preparedRecipe.ingredients)
        recipes[recipeIndex] = when (updatedRecipe) {
            is CancellableResult.Cancelled -> null
            is CancellableResult.Result -> FastCraftRecipe(this, updatedRecipe.result, fcItemStackTypeClass)
        }

        return true
    }

    private fun removeItems(items: Collection<FcItemStack>, multiplier: Int) {
        val removeAmounts = itemAmountsProvider.get()
        items.forEach { itemStack ->
            removeAmounts[itemStack] += fcItemStackTypeClass.run { itemStack.amount } * multiplier
        }

        if (removeAmounts.isEmpty()) {
            return
        }

        val removeFromSlots = fcPlayerTypeClass.run { player.inventory }.storage.asSequence()
            .filter { it.itemStack != null && fcItemStackTypeClass.run { it.itemStack!!.amount } > 0 }
            .sortedBy { fcItemStackTypeClass.run { it.itemStack!!.amount } }

        for (slot in removeFromSlots) {
            val itemStack = slot.itemStack!!
            val removeAmount = removeAmounts[itemStack]

            fcItemStackTypeClass.run {
                when {
                    itemStack.amount <= 0 -> Unit
                    removeAmount <= 0 -> Unit
                    removeAmount >= itemStack.amount -> {
                        removeAmounts[itemStack] = removeAmount - itemStack.amount
                        slot.itemStack = null
                    }
                    removeAmount < itemStack.amount -> {
                        removeAmounts[itemStack] = 0
                        slot.itemStack = itemStackFactory.copyItem(
                            itemStack = itemStack,
                            amount = itemStack.amount - removeAmount
                        )
                    }
                    else -> throw IllegalStateException()
                }
            }
        }
    }

    fun openCraftingTable() {
        fcPlayerTypeClass.run { player.openCraftingTable() }
    }

    interface Listener {
        fun onRecipesChange(recipes: List<FastCraftRecipe?>) {}
    }

    @Singleton
    class Factory @Inject constructor(
        private val itemAmountsProvider: Provider<ItemAmounts>,
        private val craftableRecipeFinder: CraftableRecipeFinder,
        private val itemStackFactory: FcItemStack.Factory,
        private val fcPlayerTypeClass: FcPlayer.TypeClass,
        private val fcItemStackTypeClass: FcItemStack.TypeClass,
    ) {
        fun create(player: FcPlayer): FastCraftGuiModel {
            return FastCraftGuiModel(
                player = player,
                itemAmountsProvider = itemAmountsProvider,
                craftableRecipeFinder = craftableRecipeFinder,
                itemStackFactory = itemStackFactory,
                fcPlayerTypeClass = fcPlayerTypeClass,
                fcItemStackTypeClass = fcItemStackTypeClass,
            )
        }
    }
}
