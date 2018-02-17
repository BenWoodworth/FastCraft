package net.benwoodworth.fastcraft.implementations.sponge.recipe

import net.benwoodworth.fastcraft.dependencies.api.item.Item
import net.benwoodworth.fastcraft.dependencies.api.player.Player
import net.benwoodworth.fastcraft.dependencies.recipe.CraftingRecipe
import net.benwoodworth.fastcraft.dependencies.recipe.Ingredient
import net.benwoodworth.fastcraft.implementations.sponge.api.item.SpongeItem
import net.benwoodworth.fastcraft.implementations.sponge.api.player.SpongePlayer
import net.benwoodworth.fastcraft.util.Adapter
import net.benwoodworth.fastcraft.util.Grid
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe as Sponge_CraftingRecipe
import org.spongepowered.api.item.recipe.crafting.Ingredient as Sponge_Ingredient

/**
 * Sponge implementation of [CraftingRecipe].
 */
abstract class SpongeCraftingRecipe private constructor(
        baseRecipe: Sponge_CraftingRecipe,
        private val plugin: Any
) : CraftingRecipe, Adapter<Sponge_CraftingRecipe>(baseRecipe) {

    override fun prepare(player: Player, items: Grid<Item>): CraftingRecipe.Prepared? {
        val grid = CustomSpongeCraftingInventory(plugin)
        val world = (player as SpongePlayer).base.world
        val result = base.getResult(grid, world).orElse(null)

        return result?.let {
            val results = mutableListOf<ItemStack>()
            results.add(it.result.createStack())
            results.addAll(it.remainingItems.map { it.createStack() })

            SpongeCraftingRecipe.Prepared(
                    player,
                    this,
                    items,
                    results.map(::SpongeItem)
            )
        }
    }

    private class Prepared(
            override val player: Player,
            override val recipe: CraftingRecipe,
            override val items: Grid<Item>,
            override val results: List<Item>
    ) : CraftingRecipe.Prepared {

        override fun craft(): List<Item>? {
            /*
             * TODO Use click event so other plugins can cancel this crafting.
             * May require https://github.com/SpongePowered/SpongeCommon/pull/1221
             */
            return results
        }
    }

    /**
     * A shaped [SpongeCraftingRecipe].
     */
    class Shaped(
            private val baseRecipe: ShapedCraftingRecipe,
            plugin: Any
    ) : SpongeCraftingRecipe(baseRecipe, plugin) {

        override val id: String
            get() = baseRecipe.id

        override val ingredients = Grid.Impl<Ingredient>(
                baseRecipe.width,
                baseRecipe.height,
                { x, y -> SpongeIngredient(baseRecipe.getIngredient(x, y)) }
        )
    }

    /**
     * A shapeless [SpongeCraftingRecipe].
     */
    class Shapeless(
            private val baseRecipe: ShapelessCraftingRecipe,
            plugin: Any
    ) : SpongeCraftingRecipe(baseRecipe, plugin) {

        override val id: String
            get() = baseRecipe.id

        override val ingredients = run {
            val predicates = baseRecipe.ingredientPredicates.iterator()

            Grid.Impl<Ingredient>(
                    3,
                    3,
                    { _, _ ->
                        predicates.takeIf { it.hasNext() }
                                ?.let { SpongeIngredient(it.next()) }
                                ?: SpongeIngredient(Sponge_Ingredient.NONE)
                    }
            )
        }
    }
}
