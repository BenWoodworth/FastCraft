package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.recipe.CraftingRecipe
import net.benwoodworth.fastcraft.dependencies.item.recipe.Ingredient
import net.benwoodworth.fastcraft.dependencies.player.Player
import net.benwoodworth.fastcraft.impl.sponge.SpongeFastCraft
import net.benwoodworth.fastcraft.util.Adapter
import net.benwoodworth.fastcraft.util.Grid
import org.spongepowered.api.item.inventory.Inventory
import org.spongepowered.api.item.inventory.InventoryArchetypes
import org.spongepowered.api.item.inventory.crafting.CraftingGridInventory
import org.spongepowered.api.item.inventory.property.InventoryDimension
import org.spongepowered.api.item.recipe.crafting.ShapedCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.ShapelessCraftingRecipe
import org.spongepowered.api.item.recipe.crafting.CraftingRecipe as Sponge_CraftingRecipe

/**
 * Sponge implementation of [CraftingRecipe].
 */
abstract class SpongeCraftingRecipe private constructor(
        private val fastCraft: SpongeFastCraft,
        baseRecipe: Sponge_CraftingRecipe
) : CraftingRecipe, Adapter<Sponge_CraftingRecipe>(baseRecipe) {

    /**
     * Create a [CraftingGridInventory] with the specified dimensions.
     *
     * @param width the inventory's width
     * @param height the inventory's height
     * @return a new [CraftingGridInventory]
     */
    private fun createCraftInv(width: Int, height: Int): CraftingGridInventory {
        return Inventory.builder()
                .of(InventoryArchetypes.WORKBENCH)
                .property(
                        InventoryDimension.PROPERTY_NAME,
                        InventoryDimension(width, height)
                )
                .build(fastCraft) as CraftingGridInventory
    }

    override fun prepare(player: Player, items: Grid<Item>): CraftingRecipe.Prepared {
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

    /**
     * A shaped [SpongeCraftingRecipe].
     */
    class Shaped(
            fastCraft: SpongeFastCraft,
            baseRecipe: ShapedCraftingRecipe
    ) : SpongeCraftingRecipe(fastCraft, baseRecipe) {

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
            fastCraft: SpongeFastCraft,
            baseRecipe: ShapelessCraftingRecipe
    ) : SpongeCraftingRecipe(fastCraft, baseRecipe) {

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
