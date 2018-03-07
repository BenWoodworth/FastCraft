package net.benwoodworth.fastcraft.implementations.sponge.recipe

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
import net.benwoodworth.fastcraft.dependencies.recipe.FcIngredient
import net.benwoodworth.fastcraft.implementations.sponge.api.item.SpongeFcItem
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.recipe.crafting.Ingredient

/**
 * Sponge implementation of [FcIngredient].
 */
class SpongeFcIngredient(
        override val base: Ingredient
) : FcIngredient, Adapter<Ingredient>() {

    override fun matches(item: FcItem): Boolean {
        return base.test((item.mutableCopy() as SpongeFcItem.Mutable).base)
    }
}
