package net.benwoodworth.fastcraft.implementations.sponge.recipe

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
import net.benwoodworth.fastcraft.dependencies.recipe.FcIngredient
import net.benwoodworth.fastcraft.implementations.sponge.api.item.SpongeFcItem
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.recipe.crafting.Ingredient as Sponge_Ingredient

/**
 * Sponge implementation of [FcIngredient].
 */
class SpongeFcIngredient(
        baseIngredient: Sponge_Ingredient
) : FcIngredient, Adapter<Sponge_Ingredient>(baseIngredient) {

    override fun matches(item: FcItem): Boolean {
        return base.test((item.mutableCopy() as SpongeFcItem.Mutable).base)
    }
}
