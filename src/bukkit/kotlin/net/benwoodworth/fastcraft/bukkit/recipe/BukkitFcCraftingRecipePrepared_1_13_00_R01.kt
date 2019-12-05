package net.benwoodworth.fastcraft.bukkit.recipe

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.bukkit.item.createFcItem
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.inventory.CraftingInventory

@AutoFactory
class BukkitFcCraftingRecipePrepared_1_13_00_R01(
    override val recipe: FcCraftingRecipe,
    private val craftingGrid: CraftingInventory,
    @Provided private val itemFactory: FcItemFactory,
    @Provided private val productProvider: IngredientProductProvider
) : BukkitFcCraftingRecipePrepared {
    override val ingredients: List<FcItem> = craftingGrid.matrix
        .map { itemFactory.createFcItem(it) }

    override fun getResultPreview(craftMaxAmount: Boolean): List<FcItem> {
        val multiplier = craftingGrid.matrix
            .map { it?.amount ?: 0 }
//            .

        val results = mutableListOf<FcItem>()

        results += itemFactory.createFcItem(craftingGrid.result)

//        results +=
        TODO()
    }

    override fun craft(craftMaxAmount: Boolean): List<FcItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
