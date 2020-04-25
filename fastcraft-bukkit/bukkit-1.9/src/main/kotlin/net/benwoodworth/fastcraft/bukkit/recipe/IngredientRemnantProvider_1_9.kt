package net.benwoodworth.fastcraft.bukkit.recipe

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class IngredientRemnantProvider_1_9 @Inject constructor(
) : IngredientRemnantProvider_1_7() {
    override fun getRemnants(ingredient: ItemStack): List<ItemStack> {
        return when (ingredient.type) {
            Material.DRAGONS_BREATH -> List(ingredient.amount) { ItemStack(Material.GLASS_BOTTLE) }
            else -> super.getRemnants(ingredient)
        }
    }
}
