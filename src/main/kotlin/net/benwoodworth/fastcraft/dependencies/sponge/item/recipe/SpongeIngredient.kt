package net.benwoodworth.fastcraft.dependencies.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.abstractions.item.Item
import net.benwoodworth.fastcraft.dependencies.abstractions.item.recipe.Ingredient
import net.benwoodworth.fastcraft.dependencies.sponge.item.SpongeItem
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
