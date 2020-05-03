package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.util.CancellableResult
import org.bukkit.Server
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.Plugin
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class BukkitFcRecipeProvider_1_7 @Inject constructor(
    plugin: Plugin,
    bukkitVersion: BukkitVersion,
    private val server: Server,
    private val recipeFactory: BukkitFcCraftingRecipe.Factory,
) : BukkitFcRecipeProvider {
    protected val complexRecipeIds: Set<String> by lazy {
        plugin.getResource("bukkit/complex-recipes.yml")
            .toYamlConfiguration()
            .getStringList(bukkitVersion.run { "$major.$minor" })
            ?.toHashSet()
            ?: emptySet()
    }

    protected open fun InputStream.toYamlConfiguration(): YamlConfiguration {
        return this.use { YamlConfiguration.loadConfiguration(this.reader()) }
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
            is ShapelessRecipe,
            -> true

            else -> false
        }
    }

    protected open fun FcCraftingRecipe.isComplexRecipe(): Boolean {
        return id in complexRecipeIds
    }

    private class ComplexFcCraftingRecipe(
        private val recipe: BukkitFcCraftingRecipe,
    ) : BukkitFcCraftingRecipe by recipe {
        override val ingredients: List<FcIngredient>
            get() = emptyList()

        override fun prepare(
            player: FcPlayer,
            ingredients: Map<FcIngredient, FcItem>,
        ): CancellableResult<FcCraftingRecipePrepared> {
            return CancellableResult.Cancelled
        }
    }
}
