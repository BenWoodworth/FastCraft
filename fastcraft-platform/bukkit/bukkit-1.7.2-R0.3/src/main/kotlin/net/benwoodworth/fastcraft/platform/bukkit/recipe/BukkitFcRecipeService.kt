package net.benwoodworth.fastcraft.platform.bukkit.recipe

import net.benwoodworth.fastcraft.platform.api.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.api.recipe.FcRecipeService
import org.bukkit.Server
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import javax.inject.Inject

/**
 * Bukkit implementation of [FcRecipeService].
 */
class BukkitFcRecipeService @Inject constructor(
    private val server: Server,
    private val shapedFactory: BukkitFcCraftingRecipe_ShapedFactory,
    private val shapelessFactory: BukkitFcCraftingRecipe_ShapelessFactory
) : FcRecipeService {

    override fun getCraftingRecipes(): List<FcCraftingRecipe> {
        val serverRecipes = server.recipeIterator()
        val results = mutableListOf<FcCraftingRecipe>()

        serverRecipes.forEach { recipe ->
            when (recipe) {
                is ShapedRecipe -> shapedFactory.create(recipe)
                is ShapelessRecipe -> shapelessFactory.create(recipe)
                else -> null
            }?.let(results::add)
        }

        return results
    }
}
