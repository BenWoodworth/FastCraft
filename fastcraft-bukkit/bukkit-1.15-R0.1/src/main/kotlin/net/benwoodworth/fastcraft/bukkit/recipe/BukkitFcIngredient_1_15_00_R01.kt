package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.item.itemStack
import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.inventory.RecipeChoice
import java.util.*

class BukkitFcIngredient_1_15_00_R01(
    val slotIndex: Int,
    val recipeChoice: RecipeChoice
) : BukkitFcIngredient {
    init {
        require(slotIndex in 0..8)
    }

    override fun matches(item: FcItem): Boolean {
        @Suppress("DEPRECATION")
        return recipeChoice.test(item.itemStack)
    }

    override fun equals(other: Any?): Boolean {
        return other is BukkitFcIngredient_1_15_00_R01 &&
                slotIndex == other.slotIndex &&
                recipeChoice == other.recipeChoice
    }

    override fun hashCode(): Int {
        return Objects.hash(slotIndex, recipeChoice)
    }
}
