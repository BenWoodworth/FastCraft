package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.Server
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import javax.inject.Inject

class BukkitFcRecipeService_1_15_00_R01 @Inject constructor(
    private val server: Server
) : BukkitFcRecipeService {
    override fun getCraftingRecipes(): Sequence<FcCraftingRecipe> {
        return server.recipeIterator()
            .asSequence()
            .mapNotNull {
                when (it) {
                    is ShapedRecipe -> BukkitFcCraftingRecipe_1_15_00_R01.Shaped(it)
                    is ShapelessRecipe -> BukkitFcCraftingRecipe_1_15_00_R01.Shapeless(it)
                    else -> null
                }
            }
    }
}
