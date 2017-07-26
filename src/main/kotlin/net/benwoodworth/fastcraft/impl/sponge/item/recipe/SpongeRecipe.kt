package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.recipe.Recipe
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.impl.sponge.item.SpongeItem
import net.benwoodworth.fastcraft.util.Adapter
import net.benwoodworth.fastcraft.util.collections.Grid
import net.benwoodworth.fastcraft.util.Memento
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.item.inventory.ItemStackSnapshot
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe
import org.spongepowered.api.item.recipe.crafting.Ingredient
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe

/**
 * Sponge implementation of [Recipe].
 */
abstract class SpongeRecipe private constructor(
        baseRecipe: CraftingRecipe,
        private val ingredientsGrid: Grid<Ingredient>
) : Recipe, Adapter<CraftingRecipe>(baseRecipe) {

    /**
     * A shaped [SpongeRecipe].
     */
    class Shaped(recipe: ShapedCraftingRecipe) : SpongeRecipe(
            recipe,
            Grid.Impl<Ingredient>(recipe.width, recipe.height) { x, y ->
                recipe.getIngredient(x, y)
            }
    )

    /**
     * A shapeless [SpongeRecipe].
     */
    class Shapeless(recipe: ShapelessCraftingRecipe) : SpongeRecipe(
            recipe,
            Grid.Impl<Ingredient>(recipe.ingredientPredicates.size, 1) { x, _ ->
                recipe.ingredientPredicates[x]
            }
    )

    override fun prepare(player: Player, vararg items: Item): List<Recipe.Prepared> {
        val spongeItems = items.map { (it as SpongeItem).base }

        /**
         * Creates a snapshot of a given item with 1 quantity.
         */
        fun itemKey(item: ItemStack) = item.copy()
                .apply { quantity = 1 }
                .createSnapshot()

        // Count the total number of all the available items
        val itemQuantities = HashMap<ItemStackSnapshot, Int>()
        spongeItems.forEach {
            val key = itemKey(it)
            itemQuantities[key] = (itemQuantities[key] ?: 0) + it.quantity
        }

        /**
         * Search through possible recipe combinations, and
         * find those that are valid.
         */
        fun search(
                depth: Int,
                grid: Grid<ItemStackSnapshot?>,
                results: MutableList<SpongeRecipe.Prepared>
        ): MutableList<SpongeRecipe.Prepared> {
            TODO("Complete search() implementation.")
        }

        TODO("Complete prepare() implementation.")
    }

    class Prepared(
            override val ingredients: List<Memento<Item>>,
            override val results: List<Memento<Item>>
    ) : Recipe.Prepared {

        override fun craft(player: Player): List<Item>? {
            return results.map { it.get() }
        }
    }
}
