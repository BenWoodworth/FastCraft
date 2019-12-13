package net.benwoodworth.fastcraft.bukkit.recipe

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import org.bukkit.inventory.CraftingInventory

@AutoFactory
class BukkitFcCraftingRecipePrepared_1_15_00_R01(
    override val recipe: BukkitFcCraftingRecipe_1_15_00_R01,
    private val craftingGrid: CraftingInventory,
    @Provided private val itemFactory: FcItemFactory,
    @Provided private val productProvider: IngredientProductProvider
) : BukkitFcCraftingRecipePrepared {
    override val ingredients: List<FcItem>
        get() = TODO()


    override fun getResultPreview(craftMaxAmount: Boolean): List<FcItem> {
        val multiplier = craftingGrid.matrix
            .map { it?.amount ?: 0 }
//            .

        val results = mutableListOf<FcItem>()

//        results += itemFactory.createFcItem(craftingGrid.result)

//        results +=
        TODO()
    }

    override fun craft(craftMaxAmount: Boolean): List<FcItem> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun equals(other: Any?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hashCode(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
