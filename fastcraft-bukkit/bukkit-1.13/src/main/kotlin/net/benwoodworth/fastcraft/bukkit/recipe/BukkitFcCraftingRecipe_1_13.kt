package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.Server
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import javax.inject.Inject
import javax.inject.Singleton

open class BukkitFcCraftingRecipe_1_13(
    recipe: Recipe,
    server: Server,
    preparedRecipeFactory: BukkitFcCraftingRecipePrepared.Factory,
    fcItemStackFactory: FcItemStack.Factory,
    craftingInventoryViewFactory: CraftingInventoryViewFactory,
    fcPlayerTypeClass: FcPlayer.TypeClass,
    private val fcItemTypeClass: FcItem.TypeClass,
    private val fcItemStackTypeClass: FcItemStack.TypeClass,
) : BukkitFcCraftingRecipe_1_12(
    recipe = recipe,
    server = server,
    fcCraftingRecipePreparedFactory = preparedRecipeFactory,
    fcItemStackFactory = fcItemStackFactory,
    craftingInventoryViewFactory = craftingInventoryViewFactory,
    fcPlayerTypeClass = fcPlayerTypeClass,
    fcItemTypeClass = fcItemTypeClass,
    fcItemStackTypeClass = fcItemStackTypeClass,
) {
    override val group: String?
        get() = when (recipe) {
            is ShapedRecipe -> recipe.group.takeUnless { it == "" }
            is ShapelessRecipe -> recipe.group.takeUnless { it == "" }
            else -> throw IllegalStateException()
        }

    override fun loadIngredients(): List<FcIngredient> {
        return when (recipe) {
            is ShapedRecipe -> recipe.shape
                .mapIndexed { row, rowString ->
                    rowString
                        .mapIndexed { column, char ->
                            recipe.choiceMap[char]?.let { choice ->
                                BukkitFcIngredient_1_13(row * 3 + column, choice, fcItemStackTypeClass)
                            }
                        }
                        .filterNotNull()
                }
                .flatten()

            is ShapelessRecipe -> recipe.choiceList
                .mapIndexed { i, recipeChoice ->
                    BukkitFcIngredient_1_13(i, recipeChoice, fcItemStackTypeClass)
                }

            else -> throw IllegalStateException()
        }
    }

    @Singleton
    class Factory @Inject constructor(
        private val server: Server,
        private val fcCraftingRecipePreparedFactory: BukkitFcCraftingRecipePrepared.Factory,
        private val fcItemStackFactory: FcItemStack.Factory,
        private val craftingInventoryViewFactory: CraftingInventoryViewFactory,
        private val fcPlayerTypeClass: FcPlayer.TypeClass,
        private val fcItemTypeClass: FcItem.TypeClass,
        private val fcItemStackTypeClass: FcItemStack.TypeClass,
    ) : BukkitFcCraftingRecipe.Factory {
        override fun create(recipe: Recipe): FcCraftingRecipe {
            return BukkitFcCraftingRecipe_1_13(
                recipe = recipe,
                server = server,
                preparedRecipeFactory = fcCraftingRecipePreparedFactory,
                fcItemStackFactory = fcItemStackFactory,
                craftingInventoryViewFactory = craftingInventoryViewFactory,
                fcPlayerTypeClass = fcPlayerTypeClass,
                fcItemTypeClass = fcItemTypeClass,
                fcItemStackTypeClass = fcItemStackTypeClass,
            )
        }
    }
}
