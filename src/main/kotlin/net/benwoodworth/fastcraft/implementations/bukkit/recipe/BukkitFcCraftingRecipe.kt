package net.benwoodworth.fastcraft.implementations.bukkit.recipe

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.dependencies.item.FcItem
import net.benwoodworth.fastcraft.dependencies.player.FcPlayer
import net.benwoodworth.fastcraft.dependencies.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.dependencies.recipe.FcIngredient
import net.benwoodworth.fastcraft.implementations.bukkit.item.BukkitFcItem
import net.benwoodworth.fastcraft.implementations.bukkit.player.BukkitFcPlayer
import net.benwoodworth.fastcraft.util.Adapter
import net.benwoodworth.fastcraft.util.Grid
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.event.inventory.*
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe

/**
 * Bukkit implementation of [FcCraftingRecipe].
 */
sealed class BukkitFcCraftingRecipe(
        override val base: Recipe
) : FcCraftingRecipe, Adapter<Recipe>() {

    protected abstract val server: Server
    protected abstract val preparedFactory: BukkitFcCraftingRecipe_PreparedFactory

    override fun prepare(player: FcPlayer, items: Grid<FcItem>): FcCraftingRecipe.Prepared? {
        val inventory = CustomBukkitCraftingInventory(
                (player as BukkitFcPlayer).base,
                base,
                server
        )

        inventory.matrix = Array(items.width * items.height) {
            (items[it].mutableCopy() as BukkitFcItem.Mutable).base
        }

        val prepareEvent = PrepareItemCraftEvent(
                inventory,
                inventory.InvView(),
                false
        )

        server.pluginManager.callEvent(prepareEvent)

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
            preparedFactory.create(
                    contents,
                    player,
                    this,
                    items,
                    inventory.getResults().map(::BukkitFcItem)
            )
        }
    }

    @AutoFactory
    class Prepared(
            private val invContents: Array<ItemStack?>,
            override val player: FcPlayer,
            override val recipe: FcCraftingRecipe,
            override val items: Grid<FcItem>,
            override val results: List<FcItem>,

            @Provided private val server: Server
    ) : FcCraftingRecipe.Prepared {

        override fun craft(): List<FcItem>? {
            val bukkitPlayer = (player as BukkitFcPlayer).base
            val bukkitRecipe = (recipe as BukkitFcCraftingRecipe).base

            val inventory = CustomBukkitCraftingInventory(
                    bukkitPlayer,
                    bukkitRecipe,
                    server
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

            server.pluginManager.callEvent(craftEvent)

            if (craftEvent.isCancelled) {
                return null
            }

            return inventory.getResults()
                    .map(::BukkitFcItem)
        }
    }

    @AutoFactory
    class Shaped(
            private val baseRecipe: ShapedRecipe,

            @Provided override val server: Server,
            @Provided override val preparedFactory: BukkitFcCraftingRecipe_PreparedFactory
    ) : BukkitFcCraftingRecipe(baseRecipe) {

        override val id: String
            get() = baseRecipe.key.toString()

        override val ingredients: Grid.Impl<FcIngredient> = Grid.Impl(3, 3) { x, y ->
            baseRecipe.shape[y]
                    .takeIf { x < it.length }
                    ?.get(x)
                    ?.let { baseRecipe.ingredientMap[it] }
                    ?.let { BukkitFcIngredient(it) }
                    ?: BukkitFcIngredient(ItemStack(Material.AIR))
        }
    }

    @AutoFactory
    class Shapeless(
            private val baseRecipe: ShapelessRecipe,

            @Provided override val server: Server,
            @Provided override val preparedFactory: BukkitFcCraftingRecipe_PreparedFactory
    ) : BukkitFcCraftingRecipe(baseRecipe) {

        override val id: String
            get() = baseRecipe.key.toString()

        override val ingredients: Grid<FcIngredient> = run {
            val ingredients = baseRecipe.ingredientList
                    .map { it?.let(::BukkitFcIngredient) }
                    .iterator()

            Grid.Impl(3, 3) { _, _ ->
                ingredients
                        .takeIf { it.hasNext() }
                        ?.next()
                        ?: BukkitFcIngredient(ItemStack(Material.AIR))
            }
        }
    }
}
