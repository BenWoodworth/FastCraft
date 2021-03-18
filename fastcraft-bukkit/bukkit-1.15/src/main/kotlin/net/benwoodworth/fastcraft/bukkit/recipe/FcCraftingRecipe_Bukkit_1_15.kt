package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.Server
import org.bukkit.inventory.ComplexRecipe
import org.bukkit.inventory.Recipe
import javax.inject.Inject
import javax.inject.Singleton

open class FcCraftingRecipe_Bukkit_1_15(
    recipe: Recipe,
    server: Server,
    preparedRecipeFactory: FcCraftingRecipePrepared_Bukkit.Factory,
    fcItemStackFactory: FcItemStack.Factory,
    inventoryViewFactory: CraftingInventoryViewFactory,
    fcPlayerOperations: FcPlayer.Operations,
    fcItemOperations: FcItem.Operations,
    fcItemStackOperations: FcItemStack.Operations,
) : FcCraftingRecipe_Bukkit_1_13(
    recipe = recipe,
    server = server,
    preparedRecipeFactory = preparedRecipeFactory,
    fcItemStackFactory = fcItemStackFactory,
    craftingInventoryViewFactory = inventoryViewFactory,
    fcPlayerOperations = fcPlayerOperations,
    fcItemOperations = fcItemOperations,
    fcItemStackOperations = fcItemStackOperations,
) {
    override fun loadIngredients(): List<FcIngredient> {
        return when (recipe) {
            is ComplexRecipe -> emptyList()
            else -> super.loadIngredients()
        }
    }

    @Singleton
    class Factory @Inject constructor(
        private val server: Server,
        private val fcCraftingRecipePreparedFactory: FcCraftingRecipePrepared_Bukkit.Factory,
        private val fcItemStackFactory: FcItemStack.Factory,
        private val craftingInventoryViewFactory: CraftingInventoryViewFactory,
        private val fcPlayerOperations: FcPlayer.Operations,
        private val fcItemOperations: FcItem.Operations,
        private val fcItemStackOperations: FcItemStack.Operations,
    ) : FcCraftingRecipe_Bukkit.Factory {
        override fun create(recipe: Recipe): FcCraftingRecipe {
            return FcCraftingRecipe_Bukkit_1_15(
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
