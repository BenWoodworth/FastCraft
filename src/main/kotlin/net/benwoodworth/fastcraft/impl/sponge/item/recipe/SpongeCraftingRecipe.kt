package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.recipe.CraftingRecipe
import net.benwoodworth.fastcraft.dependencies.item.recipe.Ingredient
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.util.Adapter
import net.benwoodworth.fastcraft.util.Grid
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe as Sponge_CraftingRecipe

/**
 * Sponge implementation of [CraftingRecipe].
 */
abstract class SpongeCraftingRecipe private constructor(
        baseRecipe: Sponge_CraftingRecipe
) : CraftingRecipe, Adapter<Sponge_CraftingRecipe>(baseRecipe) {

    override fun prepare(player: Player, items: Grid<Item>): List<Item> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun craft(player: Player, items: Grid<Item>): List<Item> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * A shaped [SpongeCraftingRecipe].
     */
    class Shaped(
            baseRecipe: ShapedCraftingRecipe
    ) : SpongeCraftingRecipe(baseRecipe) {

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
            baseRecipe: ShapelessCraftingRecipe
    ) : SpongeCraftingRecipe(baseRecipe) {

        override val ingredients = run {
            val predicates = baseRecipe.ingredientPredicates

            Grid.Impl<Ingredient>(
                    predicates.size,
                    1,
                    { x, y -> SpongeIngredient(predicates[x]) }
            )
        }
    }
}
