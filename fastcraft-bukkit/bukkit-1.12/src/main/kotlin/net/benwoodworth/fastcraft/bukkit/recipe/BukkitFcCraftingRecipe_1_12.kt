package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.Keyed
import org.bukkit.Server
import org.bukkit.inventory.Recipe
import javax.inject.Inject
import javax.inject.Singleton

open class BukkitFcCraftingRecipe_1_12(
    recipe: Recipe,
    server: Server,
    preparedRecipeFactory: BukkitFcCraftingRecipePrepared.Factory,
    itemFactory: FcItem.Factory,
    inventoryViewFactory: CraftingInventoryViewFactory,
) : BukkitFcCraftingRecipe_1_7(
    recipe = recipe,
    server = server,
    preparedRecipeFactory = preparedRecipeFactory,
    itemFactory = itemFactory,
    inventoryViewFactory = inventoryViewFactory
) {
    override val id: String
        get() = (recipe as Keyed).key.toString()

    @Singleton
    class Factory @Inject constructor(
        private val server: Server,
        private val preparedRecipeFactory: BukkitFcCraftingRecipePrepared.Factory,
        private val itemFactory: FcItem.Factory,
        private val inventoryViewFactory: CraftingInventoryViewFactory,
    ) : BukkitFcCraftingRecipe.Factory {
        override fun create(recipe: Recipe): FcCraftingRecipe {
            return BukkitFcCraftingRecipe_1_12(
                recipe = recipe,
                server = server,
                preparedRecipeFactory = preparedRecipeFactory,
                itemFactory = itemFactory,
                inventoryViewFactory = inventoryViewFactory
            )
        }
    }
}
