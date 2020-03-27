package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.Server
import org.bukkit.inventory.ComplexRecipe
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.Plugin
import javax.inject.Inject

open class BukkitFcRecipeService_1_15_R01 @Inject constructor(
    plugin: Plugin,
    server: Server,
    recipeFactory: BukkitFcCraftingRecipe.Factory
) : BukkitFcRecipeService_1_7_5_R01(
    plugin = plugin,
    server = server,
    recipeFactory = recipeFactory
) {
    override fun Recipe.isCraftingRecipe(): Boolean {
        return when (this) {
            is ShapedRecipe,
            is ShapelessRecipe,
            is ComplexRecipe -> true
            else -> false
        }
    }

    override fun FcCraftingRecipe.isComplexRecipe(): Boolean {
        return this is ComplexRecipe
    }
}
