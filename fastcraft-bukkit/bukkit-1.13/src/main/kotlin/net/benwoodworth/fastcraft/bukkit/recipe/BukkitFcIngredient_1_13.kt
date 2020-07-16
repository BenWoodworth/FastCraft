package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.world.bukkit
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.inventory.RecipeChoice
import java.util.*

open class BukkitFcIngredient_1_13 protected constructor(
    final override val slotIndex: Int,
    private val tcItemStack: FcItemStack.TypeClass,
) : BukkitFcIngredient {
    private lateinit var recipeChoice: RecipeChoice

    init {
        require(slotIndex in 0..8)
    }

    constructor(
        slotIndex: Int,
        recipeChoice: RecipeChoice,
        tcItemStack: FcItemStack.TypeClass,
    ) : this(
        slotIndex = slotIndex,
        tcItemStack = tcItemStack,
    ) {
        this.recipeChoice = recipeChoice
    }

    override fun matches(itemStack: FcItemStack): Boolean {
        tcItemStack.bukkit.run {
            return recipeChoice.test(itemStack.bukkitItemStack)
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is BukkitFcIngredient_1_13 &&
                slotIndex == other.slotIndex &&
                recipeChoice == other.recipeChoice
    }

    override fun hashCode(): Int {
        return Objects.hash(slotIndex, recipeChoice)
    }
}
