package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.util.Extensible

/**
 * A Minecraft crafting recipe.
 */
interface FcCraftingRecipe : Extensible {

    /**
     * An ID unique to this recipe. `namespace:id`
     */
    val id: String

    /**
     * The ingredients required to craft this recipe.
     */
    val ingredients: List<FcIngredient>
}

