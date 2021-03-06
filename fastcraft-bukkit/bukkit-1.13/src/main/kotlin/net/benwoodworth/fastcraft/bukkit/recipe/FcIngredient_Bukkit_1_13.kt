package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.world.FcItemStack_Bukkit
import net.benwoodworth.fastcraft.bukkit.world.bukkit
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.inventory.RecipeChoice
import java.util.*

open class FcIngredient_Bukkit_1_13 protected constructor(
    final override val slotIndex: Int,
    fcItemStackOperations: FcItemStack.Operations,
) : FcIngredient_Bukkit,
    FcItemStack_Bukkit.Operations by fcItemStackOperations.bukkit {

    private lateinit var recipeChoice: RecipeChoice

    init {
        require(slotIndex in 0..8)
    }

    constructor(
        slotIndex: Int,
        recipeChoice: RecipeChoice,
        fcItemStackOperations: FcItemStack.Operations,
    ) : this(
        slotIndex = slotIndex,
        fcItemStackOperations = fcItemStackOperations,
    ) {
        this.recipeChoice = recipeChoice
    }

    override fun matches(itemStack: FcItemStack): Boolean {
        return recipeChoice.test(itemStack.itemStack)
    }

    override fun equals(other: Any?): Boolean {
        return other is FcIngredient_Bukkit_1_13 &&
                slotIndex == other.slotIndex &&
                recipeChoice == other.recipeChoice
    }

    override fun hashCode(): Int {
        return Objects.hash(slotIndex, recipeChoice)
    }
}
