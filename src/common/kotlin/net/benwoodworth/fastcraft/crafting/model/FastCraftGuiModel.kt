package net.benwoodworth.fastcraft.crafting.model

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.util.uniqueBy
import javax.inject.Provider

@AutoFactory
class FastCraftGuiModel(
    val player: FcPlayer,
    @Provided private val itemAmountsProvider: Provider<ItemAmounts>,
    @Provided private val craftableRecipeFinder: CraftableRecipeFinder,
    @Provided private val fastCraftRecipeFactory: FastCraftRecipeFactory
) {
    var craftAmount: Int? = null

    var recipes: List<FastCraftRecipe> = emptyList()
        private set

    val inventoryItemAmounts: ItemAmounts = itemAmountsProvider.get()

    private companion object {
        val recipeComparator = compareBy<FcCraftingRecipePrepared>(
            { it.resultsPreview.first().type.id },
            { it.resultsPreview.first().type.name.toPlaintext().toLowerCase() },
            { it.resultsPreview.first().amount }
        )
    }

    fun updateInventoryItemAmounts() {
        inventoryItemAmounts.clear()

        player.inventory.storage.forEach { slot ->
            slot.item?.let { item -> inventoryItemAmounts += item }
        }
    }

    fun refreshRecipes() {
        updateInventoryItemAmounts()

        recipes = craftableRecipeFinder
            .getCraftableRecipes(player, inventoryItemAmounts)
            .uniqueBy { it.ingredientItems.toSet() to it.resultsPreview.toSet() }
            .sortedWith(recipeComparator)
            .map { fastCraftRecipeFactory.create(this, it) }
            .toList()
    }
}
