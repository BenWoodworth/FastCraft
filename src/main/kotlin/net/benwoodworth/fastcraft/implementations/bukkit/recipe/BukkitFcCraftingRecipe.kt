package net.benwoodworth.fastcraft.implementations.bukkit.recipe

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
import net.benwoodworth.fastcraft.dependencies.api.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.dependencies.recipe.FcIngredient
import net.benwoodworth.fastcraft.implementations.bukkit.api.item.BukkitFcItem
import net.benwoodworth.fastcraft.implementations.bukkit.api.player.BukkitFcPlayer
import net.benwoodworth.fastcraft.util.Adapter
import net.benwoodworth.fastcraft.util.Grid
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.*
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe

/**
 * Bukkit implementation of [FcCraftingRecipe].
 */
abstract class BukkitFcCraftingRecipe private constructor(
        baseRecipe: Recipe
) : FcCraftingRecipe, Adapter<Recipe>(baseRecipe) {

    override fun prepare(player: FcPlayer, items: Grid<FcItem>): FcCraftingRecipe.Prepared? {
        val inventory = CustomBukkitCraftingInventory(
                (player as BukkitFcPlayer).base,
                base
        )

        inventory.matrix = Array(
                items.width * items.height,
                { (items[it].mutableCopy() as BukkitFcItem.Mutable).base }
        )

        val prepareEvent = PrepareItemCraftEvent(
                inventory,
                inventory.InvView(),
                false
        )

        Bukkit.getPluginManager().callEvent(prepareEvent)

        val result = inventory.result
        if (result == null || result.type == Material.AIR) {
            return null
        }

        val contents = Array(
                inventory.contents.size,
                { inventory.contents[it]?.clone() }
        )

        // Create Prepared recipe
        return inventory.result?.let {
            BukkitFcCraftingRecipe.Prepared(
                    contents,
                    player,
                    this,
                    items,
                    inventory.getResults()
                            .map(::BukkitFcItem)
            )
        }
    }

    private class Prepared(
            private val invContents: Array<ItemStack?>,
            override val player: FcPlayer,
            override val recipe: FcCraftingRecipe,
            override val items: Grid<FcItem>,
            override val results: List<FcItem>
    ) : FcCraftingRecipe.Prepared {

        override fun craft(): List<FcItem>? {
            val bukkitPlayer = (player as BukkitFcPlayer).base
            val bukkitRecipe = (recipe as BukkitFcCraftingRecipe).base

            val inventory = CustomBukkitCraftingInventory(
                    bukkitPlayer,
                    bukkitRecipe
            )

            inventory.contents = Array(
                    invContents.size,
                    { invContents[it]?.clone() }
            )

            val craftEvent = CraftItemEvent(
                    bukkitRecipe,
                    inventory.InvView(),
                    InventoryType.SlotType.RESULT,
                    0,
                    ClickType.SHIFT_LEFT,
                    InventoryAction.MOVE_TO_OTHER_INVENTORY
            )

            Bukkit.getPluginManager().callEvent(craftEvent)

            if (craftEvent.isCancelled) {
                return null
            }

            return inventory.getResults()
                    .map(::BukkitFcItem)
        }
    }

    class Shaped(
            private val baseRecipe: ShapedRecipe
    ) : BukkitFcCraftingRecipe(baseRecipe) {

        override val id: String
            get() = baseRecipe.key.toString()

        override val ingredients: Grid.Impl<FcIngredient> = Grid.Impl(
                3,
                3,
                { x, y ->
                    baseRecipe.shape[y]
                            .takeIf { x < it.length }
                            ?.get(x)
                            ?.let { baseRecipe.ingredientMap[it] }
                            ?.let { BukkitFcIngredient(it) }
                            ?: BukkitFcIngredient(ItemStack(Material.AIR))
                }
        )
    }

    class Shapeless(
            private val baseRecipe: ShapelessRecipe
    ) : BukkitFcCraftingRecipe(baseRecipe) {

        override val id: String
            get() = baseRecipe.key.toString()

        override val ingredients: Grid<FcIngredient> = run {
            val ingredients = baseRecipe.ingredientList
                    .map { it?.let(::BukkitFcIngredient) }
                    .iterator()

            Grid.Impl(
                    3,
                    3,
                    { _, _ ->
                        ingredients
                                .takeIf { it.hasNext() }
                                ?.next()
                                ?: BukkitFcIngredient(ItemStack(Material.AIR))
                    }
            )
        }
    }
}
