package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import org.bukkit.inventory.Recipe

interface FcCraftingRecipe_Bukkit : FcCraftingRecipe {
    interface Factory {
        fun create(recipe: Recipe): FcCraftingRecipe
    }
}
