package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.world.bukkit
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import java.util.*

class BukkitFcIngredient_1_7(
    override val slotIndex: Int,
    private val ingredientItem: ItemStack,
    private val fcItemStackOperations: FcItemStack.Operations,
) : BukkitFcIngredient {
    @Suppress("DEPRECATION")
    private val hasWildcardData: Boolean = ingredientItem.data.data == WILDCARD_DATA

    init {
        require(slotIndex in 0..8)
    }

    private companion object {
        const val WILDCARD_DATA: Byte = -1
    }

    override fun matches(itemStack: FcItemStack): Boolean {
        val bukkitItemStack = fcItemStackOperations.bukkit.run { itemStack.itemStack }

        return when {
            bukkitItemStack.type === Material.AIR -> {
                ingredientItem.type == Material.AIR
            }
            hasWildcardData -> {
                ingredientItem.durability = bukkitItemStack.durability
                ingredientItem.isSimilar(bukkitItemStack)
            }
            else -> {
                ingredientItem.isSimilar(bukkitItemStack)
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is BukkitFcIngredient_1_7 &&
                slotIndex == other.slotIndex &&
                ingredientItem == other.ingredientItem
    }

    override fun hashCode(): Int {
        return Objects.hash(slotIndex, ingredientItem)
    }
}
