package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.Server
import org.bukkit.inventory.Recipe
import javax.inject.Inject

class BukkitFcCraftingRecipeFactory_1_13_00_R01 @Inject constructor(
    private val server: Server,
    private val preparedRecipeFactory: BukkitFcCraftingRecipePrepared_1_13_00_R01Factory,
    private val itemFactory: FcItemFactory,
    private val remnantProvider: IngredientRemnantProvider,
    private val inventoryViewFactory: PrepareCraftInventoryView_1_13_00_R01Factory
) : BukkitFcCraftingRecipeFactory {
    override fun create(recipe: Recipe): FcCraftingRecipe {
        return BukkitFcCraftingRecipe_1_13_00_R01(
            recipe = recipe,
            server = server,
            preparedRecipeFactory = preparedRecipeFactory,
            itemFactory = itemFactory,
            remnantProvider = remnantProvider,
            inventoryViewFactory = inventoryViewFactory
        )
    }
}
