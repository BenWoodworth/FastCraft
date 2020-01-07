package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared

data class FastCraftRecipe(
    val preparedRecipe: FcCraftingRecipePrepared,
    val multiplier: Int
)
