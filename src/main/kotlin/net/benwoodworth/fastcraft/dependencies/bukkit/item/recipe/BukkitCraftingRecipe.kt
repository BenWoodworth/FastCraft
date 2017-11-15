package net.benwoodworth.fastcraft.dependencies.bukkit.item.recipe

import net.benwoodworth.fastcraft.dependencies.abstractions.item.Item
import net.benwoodworth.fastcraft.dependencies.abstractions.item.recipe.CraftingRecipe
import net.benwoodworth.fastcraft.dependencies.abstractions.item.recipe.Ingredient
import net.benwoodworth.fastcraft.dependencies.abstractions.player.Player
import net.benwoodworth.fastcraft.dependencies.bukkit.item.BukkitItem
import net.benwoodworth.fastcraft.dependencies.bukkit.player.BukkitPlayer
import net.benwoodworth.fastcraft.util.Adapter
import net.benwoodworth.fastcraft.util.Grid
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.inventory.PrepareItemCraftEvent
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
        val inventory = CustomBukkitCraftingInventory(
                (player as BukkitPlayer).base,
                base
        )

        inventory.matrix = Array(
                items.width * items.height,
                { (items[it].mutableCopy() as BukkitItem.Mutable).base }
        )

        val prepareEvent = PrepareItemCraftEvent(
                inventory,
                inventory.View(),
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
            BukkitCraftingRecipe.Prepared(
                    contents,
                    player,
                    this,
                    items,
                    inventory.getResults()
                            .map(::BukkitItem)
            )
        }
    }

    private class Prepared(
            private val invContents: Array<ItemStack?>,
            override val player: Player,
            override val recipe: CraftingRecipe,
            override val items: Grid<Item>,
            override val results: List<Item>
    ) : CraftingRecipe.Prepared {

        override fun craft(): List<Item>? {
            val bukkitPlayer = (player as BukkitPlayer).base
            val bukkitRecipe = (recipe as BukkitCraftingRecipe).base

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
                    inventory.View(),
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
                    .map(::BukkitItem)
        }
    }

    class Shaped(
            private val baseRecipe: ShapedRecipe
    ) : BukkitCraftingRecipe(baseRecipe) {

        override val id: String
            get() = baseRecipe.key.toString()

        override val ingredients: Grid.Impl<Ingredient> = Grid.Impl(
                3,
                3,
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

    class Shapeless(
            private val baseRecipe: ShapelessRecipe
    ) : BukkitCraftingRecipe(baseRecipe) {

        override val id: String
            get() = baseRecipe.key.toString()

        override val ingredients: Grid<Ingredient> = run {
            val ingredients = baseRecipe.ingredientList
                    .map { it?.let(::BukkitIngredient) }
                    .iterator()

            Grid.Impl(
                    3,
                    3,
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
