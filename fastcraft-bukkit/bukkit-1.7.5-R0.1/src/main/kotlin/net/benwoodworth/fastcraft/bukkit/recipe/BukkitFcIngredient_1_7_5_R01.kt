package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.item.itemStack
import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*


class BukkitFcIngredient_1_7_5_R01(
    override val slotIndex: Int,
    private val ingredientItem: ItemStack
) : BukkitFcIngredient {
    @Suppress("DEPRECATION")
    private val hasWildcardData: Boolean = ingredientItem.data.data == WILDCARD_DATA

    init {
        require(slotIndex in 0..8)
    }

    private companion object {
        const val WILDCARD_DATA: Byte = -1
    }

    @Suppress("DEPRECATION")
    override fun matches(item: FcItem): Boolean {
        val bukkitItem = item.itemStack

        return when {
            bukkitItem.type === Material.AIR -> {
                ingredientItem.type == Material.AIR
            }
            hasWildcardData -> {
                ingredientItem.durability = bukkitItem.durability
                ingredientItem.isSimilar(bukkitItem)
            }
            else -> {
                ingredientItem.isSimilar(bukkitItem)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is BukkitFcIngredient_1_7_5_R01 &&
                slotIndex == other.slotIndex &&
                ingredientItem == other.ingredientItem
    }

    override fun hashCode(): Int {
        return Objects.hash(slotIndex, ingredientItem)
    }
}
