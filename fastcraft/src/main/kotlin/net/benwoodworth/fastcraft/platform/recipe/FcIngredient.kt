package net.benwoodworth.fastcraft.platform.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.util.Extensible

interface FcIngredient : Extensible {

    fun matches(item: FcItem): Boolean
}
