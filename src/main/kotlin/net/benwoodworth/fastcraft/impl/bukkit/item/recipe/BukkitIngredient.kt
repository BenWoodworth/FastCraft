package net.benwoodworth.fastcraft.impl.bukkit.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.recipe.Ingredient
import net.benwoodworth.fastcraft.impl.bukkit.item.BukkitItem
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.inventory.ItemStack

/**
 * Bukkit implementation of [Ingredient].
 */
@Suppress("DEPRECATION")
class BukkitIngredient(
        ingredient: ItemStack
) : Ingredient {

    private val typeId = ingredient.typeId

    private val durability = ingredient.durability
            .takeUnless { it == 32767.toShort() } // 32767 = match any durability

    override fun matches(item: Item): Boolean {
        val bItem = (item.toMutable() as BukkitItem.Mutable).base

        return typeId == bItem.typeId
                && durability?.equals(bItem.durability) ?: true
    }
}
