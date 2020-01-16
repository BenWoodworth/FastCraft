package net.benwoodworth.fastcraft.bukkit.recipe

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.createFcItem
import net.benwoodworth.fastcraft.bukkit.item.toItemStack
import net.benwoodworth.fastcraft.bukkit.player.player
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.util.CancellableResult
import org.bukkit.Keyed
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe

@AutoFactory
class BukkitFcCraftingRecipe_1_15_00_R01(
    val recipe: Recipe,
    @Provided val server: Server,
    @Provided val preparedRecipeFactory: BukkitFcCraftingRecipePrepared_1_15_00_R01Factory,
    @Provided val itemFactory: FcItemFactory,
    @Provided val remnantProvider: IngredientRemnantProvider,
    @Provided val inventoryViewFactory: PrepareCraftInventoryView_1_15_00_R01Factory
) : BukkitFcCraftingRecipe {
    init {
        require(recipe is ShapedRecipe || recipe is ShapelessRecipe)
    }

    override val id: String
        get() = (recipe as Keyed).key.toString()

    override val ingredients: List<FcIngredient> = when (recipe) {
        is ShapedRecipe -> recipe.shape
            .mapIndexed { row, rowString ->
                rowString
                    .mapIndexed { column, char ->
                        recipe.choiceMap[char]?.let { choice ->
                            BukkitFcIngredient_1_15_00_R01(row * 3 + column, choice)
                        }
                    }
                    .filterNotNull()
            }
            .flatten()

        is ShapelessRecipe -> recipe.choiceList
            .mapIndexed { i, recipeChoice ->
                BukkitFcIngredient_1_15_00_R01(i, recipeChoice)
            }

        else -> throw IllegalStateException()
    }

    override fun prepare(
        player: FcPlayer,
        ingredients: Map<FcIngredient, FcItem>
    ): CancellableResult<FcCraftingRecipePrepared> {
        // TODO Inventory owner
        val prepareView = inventoryViewFactory.create(player.player, null, recipe)
        val craftingGrid = prepareView.topInventory

        ingredients.forEach { (ingredient, item) ->
            ingredient as BukkitFcIngredient_1_15_00_R01

            craftingGrid.setItem(ingredient.slotIndex, item.toItemStack())
        }

        val validIngredients = ingredients.all { (ingredient, item) -> ingredient.matches(item) }
        if (validIngredients) {
            craftingGrid.result = recipe.result
        }

        val prepareEvent = PrepareItemCraftEvent(craftingGrid, prepareView, false)
        server.pluginManager.callEvent(prepareEvent)

        val resultItem = craftingGrid.result
        if (resultItem == null || resultItem.type == Material.AIR || resultItem.amount < 1) {
            return CancellableResult.Cancelled
        }

        val ingredientRemnants = ingredients.values
            .flatMap { remnantProvider.getRemnants(it.toItemStack()) }
            .map { itemFactory.createFcItem(it) }

        val resultsPreview = listOf(itemFactory.createFcItem(resultItem)) + ingredientRemnants

        return CancellableResult(
            preparedRecipeFactory.create(this, ingredients, ingredientRemnants, resultsPreview, prepareView)
        )
    }

    override fun equals(other: Any?): Boolean {
        return other is FcCraftingRecipe && id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}

