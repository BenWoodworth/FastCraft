package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.Server
import org.bukkit.inventory.ComplexRecipe
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.Plugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BukkitFcRecipeProvider_1_15 @Inject constructor(
    plugin: Plugin,
    bukkitVersion: BukkitVersion,
    server: Server,
    fcCraftingRecipeFactory: BukkitFcCraftingRecipe.Factory,
) : BukkitFcRecipeProvider_1_7(
    plugin = plugin,
    bukkitVersion = bukkitVersion,
    server = server,
    fcCraftingRecipeFactory = fcCraftingRecipeFactory
) {
    override fun Recipe.isCraftingRecipe(): Boolean {
        return when (this) {
            is ShapedRecipe,
            is ShapelessRecipe,
            is ComplexRecipe,
            -> true

            else -> false
        }
    }

    override fun FcCraftingRecipe.isComplexRecipe(): Boolean {
        return this is ComplexRecipe || this.id in complexRecipeIds
    }
}
