package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcIngredient

interface FcIngredient_Bukkit : FcIngredient {
    val slotIndex: Int
}

val FcIngredient.slotIndex: Int
    get() = (this as FcIngredient_Bukkit).slotIndex
