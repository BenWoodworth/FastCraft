package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.Keyed
import org.bukkit.Server
import org.bukkit.inventory.Recipe
import javax.inject.Inject

open class BukkitFcCraftingRecipe_1_12(
    recipe: Recipe,
    server: Server,
    preparedRecipeFactory: BukkitFcCraftingRecipePrepared_1_7Factory,
    itemFactory: FcItem.Factory,
    remnantProvider: IngredientRemnantProvider,
    inventoryViewFactory: CraftingInventoryViewFactory,
) : BukkitFcCraftingRecipe_1_7(
    recipe = recipe,
    server = server,
    preparedRecipeFactory = preparedRecipeFactory,
    itemFactory = itemFactory,
    remnantProvider = remnantProvider,
    inventoryViewFactory = inventoryViewFactory
) {
    override val id: String
        get() = (recipe as Keyed).key.toString()

    class Factory @Inject constructor(
        private val server: Server,
        private val preparedRecipeFactory: BukkitFcCraftingRecipePrepared_1_7Factory,
        private val itemFactory: FcItem.Factory,
        private val remnantProvider: IngredientRemnantProvider,
        private val inventoryViewFactory: CraftingInventoryViewFactory,
    ) : BukkitFcCraftingRecipe.Factory {
        override fun create(recipe: Recipe): FcCraftingRecipe {
            return BukkitFcCraftingRecipe_1_12(
                recipe = recipe,
                server = server,
                preparedRecipeFactory = preparedRecipeFactory,
                itemFactory = itemFactory,
                remnantProvider = remnantProvider,
                inventoryViewFactory = inventoryViewFactory
            )
        }
    }
}
