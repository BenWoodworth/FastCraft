package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.util.CancellableResult
import org.bukkit.Server
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.Plugin
import javax.inject.Inject

open class BukkitFcRecipeService_1_7_5_R01 @Inject constructor(
    plugin: Plugin,
    private val server: Server,
    private val recipeFactory: BukkitFcCraftingRecipe.Factory
) : BukkitFcRecipeService {
    protected val complexRecipeIds: Set<String> by lazy {
        plugin.getResource("bukkit-complex-recipe-ids.txt")
            .reader().useLines { lines ->
                lines.map { it.split("//").first().trim() }
                    .filter { it.isNotBlank() }
                    .toHashSet()
            }
    }

    override fun getCraftingRecipes(): Sequence<FcCraftingRecipe> {
        return server.recipeIterator()
            .asSequence()
            .filter { it.isCraftingRecipe() }
            .mapNotNull { recipeFactory.create(it) }
            .map {
                if (it.isComplexRecipe()) {
                    ComplexFcCraftingRecipe(it as BukkitFcCraftingRecipe)
                } else {
                    it
                }
            }
    }

    protected open fun Recipe.isCraftingRecipe(): Boolean {
        return when (this) {
            is ShapedRecipe,
            is ShapelessRecipe -> true
            else -> false
        }
    }

    protected open fun FcCraftingRecipe.isComplexRecipe(): Boolean {
        return id in complexRecipeIds
    }

    private class ComplexFcCraftingRecipe(
        private val recipe: BukkitFcCraftingRecipe
    ) : BukkitFcCraftingRecipe by recipe {
        override val ingredients: List<FcIngredient>
            get() = emptyList()

        override fun prepare(
            player: FcPlayer,
            ingredients: Map<FcIngredient, FcItem>
        ): CancellableResult<FcCraftingRecipePrepared> {
            return CancellableResult.Cancelled
        }
    }
}
