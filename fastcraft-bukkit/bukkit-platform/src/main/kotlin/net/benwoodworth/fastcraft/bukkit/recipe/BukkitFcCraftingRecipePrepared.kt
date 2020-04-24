package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryView

interface BukkitFcCraftingRecipePrepared : FcCraftingRecipePrepared {
    interface Factory {
        fun create(
            player: Player,
            recipe: FcCraftingRecipe,
            ingredients: Map<FcIngredient, FcItem>,
            ingredientRemnants: List<FcItem>,
            resultsPreview: List<FcItem>,
            preparedCraftingView: InventoryView,
        ): FcCraftingRecipePrepared
    }
}
