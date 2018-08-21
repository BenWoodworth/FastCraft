package net.benwoodworth.fastcraft.platform.impl.sponge.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.platform.impl.sponge.item.SpongeFcItem
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
