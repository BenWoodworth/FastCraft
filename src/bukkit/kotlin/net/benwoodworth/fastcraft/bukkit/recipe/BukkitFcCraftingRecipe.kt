package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.inventory.Recipe

interface BukkitFcCraftingRecipe : FcCraftingRecipe {
    interface Factory {
        fun create(recipe: Recipe): FcCraftingRecipe
    }
}
