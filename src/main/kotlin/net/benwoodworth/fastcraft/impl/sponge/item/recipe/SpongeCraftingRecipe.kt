package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.recipe.CraftingRecipe
import net.benwoodworth.fastcraft.dependencies.item.recipe.Ingredient
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.impl.sponge.item.SpongeItem
import net.benwoodworth.fastcraft.impl.sponge.player.SpongePlayer
import net.benwoodworth.fastcraft.util.Adapter
import net.benwoodworth.fastcraft.util.Grid
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe as Sponge_CraftingRecipe

/**
 * Sponge implementation of [CraftingRecipe].
 */
abstract class SpongeCraftingRecipe private constructor(
        baseRecipe: Sponge_CraftingRecipe,
        private val plugin: Any
) : CraftingRecipe, Adapter<Sponge_CraftingRecipe>(baseRecipe) {

    override fun prepare(player: Player, items: Grid<Item>): CraftingRecipe.Prepared? {
        val grid = CustomCraftingInventory(
                items.map { (it.toMutable() as SpongeItem.Mutable).base },
                plugin
        )

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
                    results.map(::SpongeItem),
                    plugin
            )
        }
    }

    private class Prepared(
            override val player: Player,
            override val recipe: CraftingRecipe,
            override val items: Grid<Item>,
            override val results: List<Item>,
            private val plugin: Any
    ) : CraftingRecipe.Prepared {

        override fun craft(): List<Item>? {
            val inv = CustomCraftingInventory(
                    items.map { (it.toMutable() as SpongeItem.Mutable).base },
                    plugin
            )

            TODO()
        }
    }

    /**
     * A shaped [SpongeCraftingRecipe].
     */
    class Shaped(
            baseRecipe: ShapedCraftingRecipe,
            plugin: Any
    ) : SpongeCraftingRecipe(baseRecipe, plugin) {

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
            baseRecipe: ShapelessCraftingRecipe,
            plugin: Any
    ) : SpongeCraftingRecipe(baseRecipe, plugin) {

        override val ingredients = run {
            val predicates = baseRecipe.ingredientPredicates

            Grid.Impl<Ingredient>(
                    predicates.size,
                    1,
                    { x, _ -> SpongeIngredient(predicates[x]) }
            )
        }
    }
}
