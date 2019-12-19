package net.benwoodworth.fastcraft.crafting.model

import net.benwoodworth.fastcraft.platform.item.FcItem

data class FastCraftRecipe(
    val recipe: FcItem,
    val multiplier: Int,
    val isCraftable: Boolean
)
