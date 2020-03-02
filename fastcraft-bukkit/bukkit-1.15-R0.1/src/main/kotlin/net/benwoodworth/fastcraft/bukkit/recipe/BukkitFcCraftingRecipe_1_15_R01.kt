package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import org.bukkit.Server
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import javax.inject.Inject

open class BukkitFcCraftingRecipe_1_15_R01(
    recipe: Recipe,
    server: Server,
    preparedRecipeFactory: BukkitFcCraftingRecipePrepared_1_7_5_R01Factory,
    itemFactory: FcItemFactory,
    remnantProvider: IngredientRemnantProvider,
    inventoryViewFactory: CraftingInventoryViewFactory
) : BukkitFcCraftingRecipe_1_13_R01(
    recipe = recipe,
    server = server,
    preparedRecipeFactory = preparedRecipeFactory,
    itemFactory = itemFactory,
    remnantProvider = remnantProvider,
    inventoryViewFactory = inventoryViewFactory
) {
    class Factory @Inject constructor(
        private val server: Server,
        private val preparedRecipeFactory: BukkitFcCraftingRecipePrepared_1_7_5_R01Factory,
        private val itemFactory: FcItemFactory,
        private val remnantProvider: IngredientRemnantProvider,
        private val inventoryViewFactory: CraftingInventoryViewFactory
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

    override fun loadIngredients(): List<FcIngredient> {
        return when (recipe) {
            is ShapedRecipe -> recipe.shape
                .mapIndexed { row, rowString ->
                    rowString
                        .mapIndexed { column, char ->
                            recipe.choiceMap[char]?.let { choice ->
                                BukkitFcIngredient_1_15_R01(row * 3 + column, choice)
                            }
                        }
                        .filterNotNull()
                }
                .flatten()

            is ShapelessRecipe -> recipe.choiceList
                .mapIndexed { i, recipeChoice ->
                    BukkitFcIngredient_1_15_R01(i, recipeChoice)
                }

            else -> throw IllegalStateException()
        }
    }
}

