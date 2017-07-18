package net.benwoodworth.fastcraft.impl.sponge.item.recipe

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.recipe.Ingredient
import net.benwoodworth.fastcraft.impl.sponge.item.SpongeItem
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.recipe.crafting.Ingredient as Sponge_Ingredient

/**
 * Sponge implementation of [Ingredient].
 */
class SpongeIngredient(
        baseIngredient: Sponge_Ingredient
) : Ingredient, Adapter<Sponge_Ingredient>(baseIngredient) {

    override fun matchesItem(item: Item): Boolean {
        val spongeItem = (item as SpongeItem).base
        return spongeItem.equalTo(spongeItem)
    }
}