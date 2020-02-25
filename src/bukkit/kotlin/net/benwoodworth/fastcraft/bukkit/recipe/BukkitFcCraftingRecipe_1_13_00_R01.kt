package net.benwoodworth.fastcraft.bukkit.recipe

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import org.bukkit.Server
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe

class BukkitFcCraftingRecipe_1_13_00_R01(
    recipe: Recipe,
    server: Server,
    preparedRecipeFactory: BukkitFcCraftingRecipePrepared_1_15_00_R01Factory,
    itemFactory: FcItemFactory,
    remnantProvider: IngredientRemnantProvider,
    inventoryViewFactory: PrepareCraftInventoryView_1_15_00_R01Factory
) : BukkitFcCraftingRecipe_1_15_00_R01(
    recipe = recipe,
    server = server,
    preparedRecipeFactory = preparedRecipeFactory,
    itemFactory = itemFactory,
    remnantProvider = remnantProvider,
    inventoryViewFactory = inventoryViewFactory
) {
    override fun loadIngredients(): List<FcIngredient> {
        return when (recipe) {
            is ShapedRecipe -> recipe.shape
                .mapIndexed { row, rowString ->
                    rowString
                        .mapIndexed { column, char ->
                            recipe.ingredientMap[char]?.let { ingredient ->
                                BukkitFcIngredient_1_13_00_R01(row * 3 + column, ingredient)
                            }
                        }
                        .filterNotNull()
                }
                .flatten()

            is ShapelessRecipe -> recipe.ingredientList
                .mapIndexed { i, ingredient ->
                    BukkitFcIngredient_1_13_00_R01(i, ingredient)
                }

            else -> throw IllegalStateException()
        }
    }
}

