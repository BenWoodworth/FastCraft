package net.benwoodworth.fastcraft.impl.bukkit.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.recipe.CraftingRecipe
import net.benwoodworth.fastcraft.dependencies.item.recipe.Ingredient
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.util.Adapter
import net.benwoodworth.fastcraft.util.Grid
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.inventory.Recipe as Bukkit_Recipe

/**
 * Bukkit implementation of [CraftingRecipe].
 */
abstract class BukkitCraftingRecipe private constructor(
        baseRecipe: Bukkit_Recipe
) : CraftingRecipe, Adapter<Bukkit_Recipe>(baseRecipe) {

    override fun prepare(player: Player, items: Grid<Item>): CraftingRecipe.Prepared? {
        TODO("not implemented")
    }

    private class Prepared(
        override val player: Player,
        override val recipe: CraftingRecipe,
        override val items: Grid<Item>,
        override val results: List<Item>
    ) : CraftingRecipe.Prepared {

        override fun craft(): List<Item>? {
            TODO("not implemented")
        }
    }

    class Shaped(
            private val baseRecipe: ShapedRecipe
    ) : BukkitCraftingRecipe(baseRecipe) {

        override val ingredients: Grid.Impl<Ingredient> = run {
            val width = baseRecipe.shape.map { it.length }.max() ?: 0
            val height = baseRecipe.shape.size

            Grid.Impl(
                    width,
                    height,
                    { x, y ->
                        baseRecipe.shape[y]
                                .takeIf { x < it.length }
                                ?.get(x)
                                ?.let { baseRecipe.ingredientMap[it] }
                                ?.let { BukkitIngredient(it) }
                                ?: BukkitIngredient(ItemStack(Material.AIR))
                    }
            )
        }
    }

    class Shapeless(
            private val baseRecipe: ShapelessRecipe
    ) : BukkitCraftingRecipe(baseRecipe) {

        override val ingredients: Grid<Ingredient> = run {
            val size = baseRecipe.ingredientList.size
            val dim = Math.ceil(Math.sqrt(size.toDouble())).toInt()

            val ingredients = baseRecipe.ingredientList
                    .map { it?.let(::BukkitIngredient) }
                    .iterator()

            Grid.Impl(
                    dim,
                    dim,
                    { _, _ ->
                        ingredients
                                .takeIf { it.hasNext() }
                                ?.next()
                                ?: BukkitIngredient(ItemStack(Material.AIR))
                    }
            )
        }
    }
}
