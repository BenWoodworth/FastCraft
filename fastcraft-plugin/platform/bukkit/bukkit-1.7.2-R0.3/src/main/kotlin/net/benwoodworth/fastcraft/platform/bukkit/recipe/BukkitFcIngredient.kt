package net.benwoodworth.fastcraft.platform.bukkit.recipe

import net.benwoodworth.fastcraft.platform.api.item.FcItem
import net.benwoodworth.fastcraft.platform.api.recipe.FcIngredient
import net.benwoodworth.fastcraft.platform.bukkit.item.BukkitFcItem
import org.bukkit.inventory.ItemStack

/**
 * Bukkit implementation of [FcIngredient].
 */
@Suppress("DEPRECATION")
class BukkitFcIngredient(
        ingredient: ItemStack
) : FcIngredient {

    private val typeId = ingredient.typeId

    private val durability = ingredient.durability
            .takeUnless { it == 32767.toShort() } // 32767 = match any durability

    override fun matches(item: FcItem): Boolean {
        val bItem = (item.mutableCopy() as BukkitFcItem.Mutable).base

        return typeId == bItem.typeId
                && durability?.equals(bItem.durability) ?: true
    }
}
