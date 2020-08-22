package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.player.bukkit
import net.benwoodworth.fastcraft.bukkit.world.bukkit
import net.benwoodworth.fastcraft.bukkit.world.create
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import net.benwoodworth.fastcraft.util.CancellableResult
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.inventory.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

open class BukkitFcCraftingRecipe_1_7(
    val recipe: Recipe,
    private val server: Server,
    private val preparedRecipeFactory: BukkitFcCraftingRecipePrepared.Factory,
    private val fcItemStackFactory: FcItemStack.Factory,
    private val inventoryViewFactory: CraftingInventoryViewFactory,
    private val fcPlayerOperations: FcPlayer.Operations,
    private val fcItemOperations: FcItem.Operations,
    private val fcItemStackOperations: FcItemStack.Operations,
) : BukkitFcCraftingRecipe {
    private companion object {
        private const val recipeIdAlphabet = "23456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefhkmnorsuvwxz"
        private const val recipeIdLength = 6 // ceil(log_50(2^32))

        fun Int.toRecipeId(): String {
            var idNum = this.toLong() - Int.MIN_VALUE

            val recipeId = CharArray(recipeIdLength)
            for (i in 0 until recipeIdLength) {
                recipeId[i] = recipeIdAlphabet[(idNum % recipeIdAlphabet.length).toInt()]
                idNum /= recipeIdAlphabet.length
            }

            return String(recipeId)
        }
    }

    override val id: String
        get() = hashCode().toRecipeId()

    override val ingredients: List<FcIngredient> by lazy {
        loadIngredients()
    }

    protected open fun loadIngredients(): List<FcIngredient> {
        return when (recipe) {
            is ShapedRecipe -> recipe.shape
                .mapIndexed { row, rowString ->
                    rowString
                        .mapIndexed { column, char ->
                            recipe.ingredientMap[char]?.let { ingredient ->
                                BukkitFcIngredient_1_7(row * 3 + column, ingredient, fcItemStackOperations)
                            }
                        }
                        .filterNotNull()
                }
                .flatten()

            is ShapelessRecipe -> recipe.ingredientList
                .mapIndexed { i, ingredient ->
                    BukkitFcIngredient_1_7(i, ingredient, fcItemStackOperations)
                }

            else -> throw IllegalStateException()
        }
    }

    override val group: String?
        get() = null

    override val exemplaryResult: FcItemStack
        get() = recipe.result
            ?.let { fcItemStackFactory.create(it) }
            ?: fcItemStackFactory.create(ItemStack(Material.AIR))

    override fun prepare(
        player: FcPlayer,
        ingredients: Map<FcIngredient, FcItemStack>,
    ): CancellableResult<FcCraftingRecipePrepared> {
        // TODO Inventory owner
        val prepareView = inventoryViewFactory.create(fcPlayerOperations.bukkit.run { player.player }, null, recipe)
        val craftingGrid = prepareView.topInventory as CraftingInventory

        fcItemStackOperations.bukkit.run {
            ingredients.forEach { (ingredient, itemStack) ->
                craftingGrid.setItem(ingredient.slotIndex, itemStack.itemStack.clone())
            }
        }

        val validIngredients = ingredients.all { (ingredient, itemStack) -> ingredient.matches(itemStack) }
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
            .mapNotNull { ingredient ->
                fcItemOperations.bukkit.run {
                    fcItemStackOperations.run {
                        ingredient.type.craftingRemainingItem
                            ?.let { fcItemStackFactory.create(ItemStack(it.material, ingredient.amount)) }
                    }
                }
            }

        val resultsPreview = listOf(fcItemStackFactory.create(resultItem)) + ingredientRemnants

        return CancellableResult(
            preparedRecipeFactory.create(
                fcPlayerOperations.bukkit.run { player.player },
                this,
                ingredients,
                ingredientRemnants,
                resultsPreview,
                prepareView,
            )
        )
    }

    override fun equals(other: Any?): Boolean {
        if (other !is BukkitFcCraftingRecipe_1_7) {
            return false
        }

        val otherRecipe = other.recipe

        return when {
            recipe is ShapedRecipe && otherRecipe is ShapedRecipe -> {
                recipe.ingredientMap == otherRecipe.ingredientMap &&
                        recipe.shape!!.contentEquals(otherRecipe.shape) &&
                        recipe.result == otherRecipe.result

            }
            recipe is ShapelessRecipe && otherRecipe is ShapelessRecipe -> {
                recipe.ingredientList == otherRecipe.ingredientList &&
                        recipe.result == otherRecipe.result
            }
            else -> {
                return false
            }
        }
    }

    override fun hashCode(): Int {
        return when (recipe) {
            is ShapedRecipe -> {
                Objects.hash(
                    recipe.result,
                    Arrays.hashCode(recipe.shape),
                    recipe.ingredientMap
                )
            }
            is ShapelessRecipe -> {
                Objects.hash(
                    recipe.result,
                    recipe.ingredientList,
                )
            }
            else -> error("Only ShapedRecipe and ShapelessRecipe are supported")
        }
    }

    @Singleton
    class Factory @Inject constructor(
        private val server: Server,
        private val fcCraftingRecipePreparedFactory: BukkitFcCraftingRecipePrepared.Factory,
        private val fcItemStackFactory: FcItemStack.Factory,
        private val craftingInventoryViewFactory: CraftingInventoryViewFactory,
        private val fcPlayerOperations: FcPlayer.Operations,
        private val fcItemOperations: FcItem.Operations,
        private val fcItemStackOperations: FcItemStack.Operations,
    ) : BukkitFcCraftingRecipe.Factory {
        override fun create(recipe: Recipe): FcCraftingRecipe {
            return BukkitFcCraftingRecipe_1_7(
                recipe = recipe,
                server = server,
                preparedRecipeFactory = fcCraftingRecipePreparedFactory,
                fcItemStackFactory = fcItemStackFactory,
                inventoryViewFactory = craftingInventoryViewFactory,
                fcPlayerOperations = fcPlayerOperations,
                fcItemOperations = fcItemOperations,
                fcItemStackOperations = fcItemStackOperations,
            )
        }
    }
}
