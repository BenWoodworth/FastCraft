package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcIngredient

interface BukkitFcIngredient : FcIngredient {
    val slotIndex: Int

    companion object {
        val FcIngredient.slotIndex: Int
            get() = (this as BukkitFcIngredient).slotIndex
    }
}
