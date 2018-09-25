package net.benwoodworth.fastcraft.platform.sponge.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.platform.sponge.item.SpongeFcItem
import net.benwoodworth.fastcraft.platform.sponge.player.SpongeFcPlayer
import net.benwoodworth.fastcraft.util.Adapter
import net.benwoodworth.fastcraft.util.Grid
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe
import org.spongepowered.api.item.recipe.crafting.Ingredient
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe

/**
 * Sponge implementation of [FcCraftingRecipe].
 */
abstract class SpongeFcCraftingRecipe private constructor(
        override val base: CraftingRecipe,
        private val plugin: Any
) : FcCraftingRecipe, Adapter<CraftingRecipe>() {

    override fun prepare(player: FcPlayer, items: Grid<FcItem>): FcCraftingRecipe.Prepared? {
        val grid = CustomSpongeCraftingInventory(plugin)
        val world = (player as SpongeFcPlayer).base.world
        val result = base.getResult(grid, world).orElse(null)

        return result?.let {
            val results = mutableListOf<ItemStack>()
            results.add(it.result.createStack())
            results.addAll(it.remainingItems.map { it.createStack() })

            SpongeFcCraftingRecipe.Prepared(
                    player,
                    this,
                    items,
                    results.map(::SpongeFcItem)
            )
        }
    }

    private class Prepared(
            override val player: FcPlayer,
            override val recipe: FcCraftingRecipe,
            override val items: Grid<FcItem>,
            override val results: List<FcItem>
    ) : FcCraftingRecipe.Prepared {

        override fun craft(): List<FcItem>? {
            /*
             * TODO Use click event so other plugins can cancel this crafting.
             * May require https://github.com/SpongePowered/SpongeCommon/pull/1221
             */
            return results
        }
    }

    /**
     * A shaped [SpongeFcCraftingRecipe].
     */
    class Shaped(
            private val baseRecipe: ShapedCraftingRecipe,
            plugin: Any
    ) : SpongeFcCraftingRecipe(baseRecipe, plugin) {

        override val id: String
            get() = baseRecipe.id

        override val ingredients = Grid.Impl<FcIngredient>(
                baseRecipe.width,
                baseRecipe.height,
                { x, y -> SpongeFcIngredient(baseRecipe.getIngredient(x, y)) }
        )
    }

    /**
     * A shapeless [SpongeFcCraftingRecipe].
     */
    class Shapeless(
            private val baseRecipe: ShapelessCraftingRecipe,
            plugin: Any
    ) : SpongeFcCraftingRecipe(baseRecipe, plugin) {

        override val id: String
            get() = baseRecipe.id

        override val ingredients = run {
            val predicates = baseRecipe.ingredientPredicates.iterator()

            Grid.Impl<FcIngredient>(
                    3,
                    3,
                    { _, _ ->
                        predicates.takeIf { it.hasNext() }
                                ?.let { SpongeFcIngredient(it.next()) }
                                ?: SpongeFcIngredient(Ingredient.NONE)
                    }
            )
        }
    }
}
