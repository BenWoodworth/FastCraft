package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryView

interface BukkitFcCraftingRecipePrepared : FcCraftingRecipePrepared {
    interface Factory {
        fun create(
            player: Player,
            recipe: FcCraftingRecipe,
            ingredients: Map<FcIngredient, FcItemStack>,
            ingredientRemnants: List<FcItemStack>,
            resultsPreview: List<FcItemStack>,
            preparedCraftingView: InventoryView,
        ): FcCraftingRecipePrepared
    }
}
