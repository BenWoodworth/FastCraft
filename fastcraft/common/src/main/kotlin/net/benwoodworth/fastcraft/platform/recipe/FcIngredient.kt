package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem

interface FcIngredient {

    fun matches(item: FcItem): Boolean
}
