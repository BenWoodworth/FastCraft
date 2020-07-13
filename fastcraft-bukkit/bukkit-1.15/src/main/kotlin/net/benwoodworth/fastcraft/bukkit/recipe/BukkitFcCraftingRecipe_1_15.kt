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

open class BukkitFcCraftingRecipe_1_15(
    recipe: Recipe,
    server: Server,
    preparedRecipeFactory: BukkitFcCraftingRecipePrepared.Factory,
    itemStackFactory: FcItemStack.Factory,
    inventoryViewFactory: CraftingInventoryViewFactory,
    tcPlayer: FcPlayer.TypeClass,
    tcItem: FcItem.TypeClass,
) : BukkitFcCraftingRecipe_1_13(
    recipe = recipe,
    server = server,
    preparedRecipeFactory = preparedRecipeFactory,
    itemStackFactory = itemStackFactory,
    inventoryViewFactory = inventoryViewFactory,
    tcPlayer = tcPlayer,
    tcItem = tcItem,
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
        private val preparedRecipeFactory: BukkitFcCraftingRecipePrepared.Factory,
        private val itemStackFactory: FcItemStack.Factory,
        private val inventoryViewFactory: CraftingInventoryViewFactory,
        private val tcPlayer: FcPlayer.TypeClass,
        private val tcItem: FcItem.TypeClass,
    ) : BukkitFcCraftingRecipe.Factory {
        override fun create(recipe: Recipe): FcCraftingRecipe {
            return BukkitFcCraftingRecipe_1_15(
                recipe = recipe,
                server = server,
                preparedRecipeFactory = preparedRecipeFactory,
                itemStackFactory = itemStackFactory,
                inventoryViewFactory = inventoryViewFactory,
                tcPlayer = tcPlayer,
                tcItem = tcItem,
            )
        }
    }
}
