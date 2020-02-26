package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.item.itemStack
import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*


class BukkitFcIngredient_1_13_R01(
    slotIndex: Int,
    private val ingredientItem: ItemStack
) : BukkitFcIngredient_1_15_R01(
    slotIndex = slotIndex
) {
    init {
        require(slotIndex in 0..8)
    }

    private companion object {
        const val WILDCARD_DATA: Short = -1
    }

    @Suppress("DEPRECATION")
    override fun matches(item: FcItem): Boolean {
        val bukkitItem = item.itemStack

        return when {
            bukkitItem.type === Material.AIR -> {
                ingredientItem.type == Material.AIR
            }
            ingredientItem.durability == WILDCARD_DATA -> {
                ingredientItem.durability = bukkitItem.durability

                ingredientItem.isSimilar(bukkitItem)
                    .also { ingredientItem.durability = WILDCARD_DATA }
            }
            else -> {
                ingredientItem.isSimilar(bukkitItem)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is BukkitFcIngredient_1_13_R01 &&
                slotIndex == other.slotIndex &&
                ingredientItem == other.ingredientItem
    }

    override fun hashCode(): Int {
        return Objects.hash(slotIndex, ingredientItem)
    }
}
