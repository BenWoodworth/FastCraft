package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.Server
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import javax.inject.Inject

open class BukkitFcRecipeService_1_15_00_R01 @Inject constructor(
    private val server: Server,
    private val recipeFactory: BukkitFcCraftingRecipeFactory
) : BukkitFcRecipeService {
    override fun getCraftingRecipes(): Sequence<FcCraftingRecipe> {
        return server.recipeIterator()
            .asSequence()
            .mapNotNull { recipe ->
                when (recipe) {
                    is ShapedRecipe,
                    is ShapelessRecipe -> recipeFactory.create(recipe)
                    else -> null
                }
            }
    }
}
