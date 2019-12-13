package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import javax.inject.Inject

class BukkitRecipeConverter_1_15_00_R01 @Inject constructor(
    private val recipeFactory: BukkitFcCraftingRecipe_1_15_00_R01Factory
) : BukkitRecipeConverter {
    override fun convertRecipe(recipe: Recipe): FcCraftingRecipe? {
        return when (recipe) {
            is ShapedRecipe,
            is ShapelessRecipe -> recipeFactory.create(recipe)
            else -> null
        }
    }
}
