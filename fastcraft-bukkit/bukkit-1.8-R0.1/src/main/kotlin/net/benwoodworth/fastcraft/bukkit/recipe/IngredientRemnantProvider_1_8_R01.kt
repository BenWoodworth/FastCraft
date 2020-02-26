package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class IngredientRemnantProvider_1_8_R01 @Inject constructor(
) : IngredientRemnantProvider {
    override fun getRemnants(ingredient: ItemStack): List<ItemStack> {
        return when (ingredient.type) {
            Material.LAVA_BUCKET,
            Material.MILK_BUCKET,
            Material.WATER_BUCKET -> {
                List(ingredient.amount) { ItemStack(Material.BUCKET) }
            }

            else -> emptyList()
        }
    }
}
