package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class IngredientProductProvider_1_15_00_R01 @Inject constructor(
) : IngredientProductProvider {
    override fun getIngredientProducts(item: ItemStack): List<ItemStack> {
        return when (item.type) {
            Material.LAVA_BUCKET,
            Material.MILK_BUCKET,
            Material.WATER_BUCKET -> {
                List(item.amount) { ItemStack(Material.BUCKET) }
            }

            else -> emptyList()
        }
    }
}
