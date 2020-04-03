package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import org.bukkit.Server
import org.bukkit.inventory.ComplexRecipe
import org.bukkit.inventory.Recipe
import javax.inject.Inject

open class BukkitFcCraftingRecipe_1_15_R01(
    recipe: Recipe,
    server: Server,
    preparedRecipeFactory: BukkitFcCraftingRecipePrepared_1_7_5_R01Factory,
    itemFactory: FcItem.Factory,
    remnantProvider: IngredientRemnantProvider,
    inventoryViewFactory: CraftingInventoryViewFactory,
) : BukkitFcCraftingRecipe_1_13_1_R01(
    recipe = recipe,
    server = server,
    preparedRecipeFactory = preparedRecipeFactory,
    itemFactory = itemFactory,
    remnantProvider = remnantProvider,
    inventoryViewFactory = inventoryViewFactory
) {
    override fun loadIngredients(): List<FcIngredient> {
        return when (recipe) {
            is ComplexRecipe -> emptyList()
            else -> super.loadIngredients()
        }
    }

    class Factory @Inject constructor(
        private val server: Server,
        private val preparedRecipeFactory: BukkitFcCraftingRecipePrepared_1_7_5_R01Factory,
        private val itemFactory: FcItem.Factory,
        private val remnantProvider: IngredientRemnantProvider,
        private val inventoryViewFactory: CraftingInventoryViewFactory,
    ) : BukkitFcCraftingRecipe.Factory {
        override fun create(recipe: Recipe): FcCraftingRecipe {
            return BukkitFcCraftingRecipe_1_15_R01(
                recipe = recipe,
                server = server,
                preparedRecipeFactory = preparedRecipeFactory,
                itemFactory = itemFactory,
                remnantProvider = remnantProvider,
                inventoryViewFactory = inventoryViewFactory
            )
        }
    }
}
