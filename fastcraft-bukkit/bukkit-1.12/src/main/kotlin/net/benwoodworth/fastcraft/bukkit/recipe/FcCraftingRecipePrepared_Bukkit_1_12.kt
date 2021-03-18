package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.Server
import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import javax.inject.Inject
import javax.inject.Singleton

open class FcCraftingRecipePrepared_Bukkit_1_12(
    player: Player,
    recipe: FcCraftingRecipe,
    ingredients: Map<FcIngredient, FcItemStack>,
    ingredientRemnants: List<FcItemStack>,
    resultsPreview: List<FcItemStack>,
    preparedCraftingView: InventoryView,
    fcItemStackFactory: FcItemStack.Factory,
    server: Server,
) : FcCraftingRecipePrepared_Bukkit_1_7(
    player = player,
    recipe = recipe,
    ingredients = ingredients,
    ingredientRemnants = ingredientRemnants,
    resultsPreview = resultsPreview,
    preparedCraftingView = preparedCraftingView,
    fcItemStackFactory = fcItemStackFactory,
    server = server,
) {
    override fun onCraft(result: ItemStack) {
        incrementCraftStats(result)
    }

    @Singleton
    class Factory @Inject constructor(
        private val fcItemStackFactory: FcItemStack.Factory,
        private val server: Server,
    ) : FcCraftingRecipePrepared_Bukkit.Factory {
        override fun create(
            player: Player,
            recipe: FcCraftingRecipe,
            ingredients: Map<FcIngredient, FcItemStack>,
            ingredientRemnants: List<FcItemStack>,
            resultsPreview: List<FcItemStack>,
            preparedCraftingView: InventoryView,
        ): FcCraftingRecipePrepared {
            return FcCraftingRecipePrepared_Bukkit_1_12(
                player = player,
                recipe = recipe,
                ingredients = ingredients,
                ingredientRemnants = ingredientRemnants,
                resultsPreview = resultsPreview,
                preparedCraftingView = preparedCraftingView,
                fcItemStackFactory = fcItemStackFactory,
                server = server,
            )
        }
    }
}
