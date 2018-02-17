package net.benwoodworth.fastcraft.implementations.sponge.recipe

import net.benwoodworth.fastcraft.dependencies.api.item.Item
import net.benwoodworth.fastcraft.dependencies.recipe.Ingredient
import net.benwoodworth.fastcraft.implementations.sponge.api.item.SpongeItem
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.recipe.crafting.Ingredient as Sponge_Ingredient

/**
 * Sponge implementation of [Ingredient].
 */
class SpongeIngredient(
        baseIngredient: Sponge_Ingredient
) : Ingredient, Adapter<Sponge_Ingredient>(baseIngredient) {

    override fun matches(item: Item): Boolean {
        return base.test((item.mutableCopy() as SpongeItem.Mutable).base)
    }
}
