package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.world.FcItem
import net.benwoodworth.fastcraft.platform.world.FcItemStack
import org.bukkit.Keyed
import org.bukkit.Server
import org.bukkit.inventory.Recipe
import javax.inject.Inject
import javax.inject.Singleton

open class BukkitFcCraftingRecipe_1_12(
    recipe: Recipe,
    server: Server,
    fcCraftingRecipePreparedFactory: BukkitFcCraftingRecipePrepared.Factory,
    fcItemStackFactory: FcItemStack.Factory,
    craftingInventoryViewFactory: CraftingInventoryViewFactory,
    fcPlayerTypeClass: FcPlayer.TypeClass,
    fcItemTypeClass: FcItem.TypeClass,
    fcItemStackTypeClass: FcItemStack.TypeClass,
) : BukkitFcCraftingRecipe_1_7(
    recipe = recipe,
    server = server,
    preparedRecipeFactory = fcCraftingRecipePreparedFactory,
    fcItemStackFactory = fcItemStackFactory,
    inventoryViewFactory = craftingInventoryViewFactory,
    fcPlayerTypeClass = fcPlayerTypeClass,
    fcItemTypeClass = fcItemTypeClass,
    fcItemStackTypeClass = fcItemStackTypeClass,
) {
    override val id: String
        get() = (recipe as Keyed).key.toString()

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
            return BukkitFcCraftingRecipe_1_12(
                recipe = recipe,
                server = server,
                fcCraftingRecipePreparedFactory = fcCraftingRecipePreparedFactory,
                fcItemStackFactory = fcItemStackFactory,
                craftingInventoryViewFactory = craftingInventoryViewFactory,
                fcPlayerTypeClass = fcPlayerTypeClass,
                fcItemTypeClass = fcItemTypeClass,
                fcItemStackTypeClass = fcItemStackTypeClass,
            )
        }
    }
}
