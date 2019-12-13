package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.Server
import javax.inject.Inject

class BukkitFcRecipeService_1_15_00_R01 @Inject constructor(
    private val server: Server,
    private val recipeConverter: BukkitRecipeConverter
) : BukkitFcRecipeService {
    override fun getCraftingRecipes(): Sequence<FcCraftingRecipe> {
        return server.recipeIterator()
            .asSequence()
            .mapNotNull {
                recipeConverter.convertRecipe(it)
            }
    }
}
