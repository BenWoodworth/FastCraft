package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import javax.inject.Inject
import javax.inject.Singleton

open class BukkitFcCraftingRecipePrepared_1_12(
    player: Player,
    recipe: FcCraftingRecipe,
    ingredients: Map<FcIngredient, FcItem>,
    ingredientRemnants: List<FcItem>,
    resultsPreview: List<FcItem>,
    preparedCraftingView: InventoryView,
    itemFactory: FcItem.Factory,
    server: Server,
) : BukkitFcCraftingRecipePrepared_1_7(
    player = player,
    recipe = recipe,
    ingredients = ingredients,
    ingredientRemnants = ingredientRemnants,
    resultsPreview = resultsPreview,
    preparedCraftingView = preparedCraftingView,
    itemFactory = itemFactory,
    server = server,
) {
    override fun onCraft(result: ItemStack) {
        incrementCraftStats(result)
    }

    @Singleton
    class Factory @Inject constructor(
        private val itemFactory: FcItem.Factory,
        private val server: Server,
    ) : BukkitFcCraftingRecipePrepared.Factory {
        override fun create(
            player: Player,
            recipe: FcCraftingRecipe,
            ingredients: Map<FcIngredient, FcItem>,
            ingredientRemnants: List<FcItem>,
            resultsPreview: List<FcItem>,
            preparedCraftingView: InventoryView,
        ): FcCraftingRecipePrepared {
            return BukkitFcCraftingRecipePrepared_1_12(
                player = player,
                recipe = recipe,
                ingredients = ingredients,
                ingredientRemnants = ingredientRemnants,
                resultsPreview = resultsPreview,
                preparedCraftingView = preparedCraftingView,
                itemFactory = itemFactory,
                server = server,
            )
        }
    }
}
