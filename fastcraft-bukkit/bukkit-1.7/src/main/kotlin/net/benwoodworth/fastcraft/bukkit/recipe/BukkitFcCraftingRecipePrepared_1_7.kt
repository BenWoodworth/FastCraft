package net.benwoodworth.fastcraft.bukkit.recipe

import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItem.Factory.Companion.createFcItem
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipe
import net.benwoodworth.fastcraft.platform.recipe.FcCraftingRecipePrepared
import net.benwoodworth.fastcraft.platform.recipe.FcIngredient
import net.benwoodworth.fastcraft.util.CancellableResult
import org.bukkit.Achievement
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.Statistic
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.CraftingInventory
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

open class BukkitFcCraftingRecipePrepared_1_7(
    protected val player: Player,
    override val recipe: FcCraftingRecipe,
    override val ingredients: Map<FcIngredient, FcItem>,
    private val ingredientRemnants: List<FcItem>,
    override val resultsPreview: List<FcItem>,
    private val preparedCraftingView: InventoryView,
    private val itemFactory: FcItem.Factory,
    private val server: Server,
) : BukkitFcCraftingRecipePrepared {
    private var craftCalled = false

    override fun craft(): CancellableResult<List<FcItem>> {
        require(!craftCalled) { "Only callable once" }
        craftCalled = true

        val craftingInventory = preparedCraftingView.topInventory as CraftingInventory

        val craftEvent = CraftItemEvent(
            craftingInventory.recipe!!,
            preparedCraftingView,
            InventoryType.SlotType.RESULT,
            9,
            ClickType.SHIFT_LEFT,
            InventoryAction.MOVE_TO_OTHER_INVENTORY
        )

        server.pluginManager.callEvent(craftEvent)

        val resultItem = craftingInventory.result
        val isCancelled = craftEvent.isCancelled || resultItem == null || resultItem.amount < 1

        return if (isCancelled) {
            CancellableResult.Cancelled
        } else {
            onCraft(resultItem)
            CancellableResult(
                listOf(itemFactory.createFcItem(resultItem!!)) + ingredientRemnants
            )
        }
    }

    protected open fun onCraft(result: ItemStack) {
        giveCraftAchievements(result.type)
        incrementCraftStats(result)
    }

    protected open fun giveCraftAchievements(material: Material) {
        val achievement = when (material) {
            Material.STONE_AXE -> Achievement.BUILD_BETTER_PICKAXE
            Material.FURNACE -> Achievement.BUILD_FURNACE
            Material.WOOD_PICKAXE -> Achievement.BUILD_PICKAXE
            Material.WORKBENCH -> Achievement.BUILD_WORKBENCH
            Material.CAKE -> Achievement.BAKE_CAKE

            Material.DIAMOND_SWORD,
            Material.GOLD_SWORD,
            Material.IRON_SWORD,
            Material.STONE_SWORD,
            Material.WOOD_SWORD,
            -> Achievement.BUILD_SWORD

            Material.DIAMOND_HOE,
            Material.GOLD_HOE,
            Material.IRON_HOE,
            Material.STONE_HOE,
            Material.WOOD_HOE,
            -> Achievement.BUILD_HOE

            else -> return
        }

        when {
            player.hasAchievement(achievement) -> return
            achievement.hasParent() && !player.hasAchievement(achievement.parent) -> return
            else -> player.awardAchievement(achievement)
        }
    }

    protected open fun incrementCraftStats(result: ItemStack) {
        player.incrementStatistic(Statistic.CRAFT_ITEM, result.type, result.amount)
    }

    class Factory @Inject constructor(
        private val itemFactory: FcItem.Factory,
        private val server: Server,
    ) : BukkitFcCraftingRecipePrepared.Factory {
        override fun create(
            player: Player,
            recipe: FcCraftingRecipe,
            ingredients: Map<FcIngredient, FcItem>,
            ingredientRemnants: List<FcItem>,
            resultsPreview: List<FcItem>,
            preparedCraftingView: InventoryView,
        ): FcCraftingRecipePrepared {
            return BukkitFcCraftingRecipePrepared_1_7_5_R01(
                player = player,
                recipe = recipe,
                ingredients = ingredients,
                ingredientRemnants = ingredientRemnants,
                resultsPreview = resultsPreview,
                preparedCraftingView = preparedCraftingView,
                itemFactory = itemFactory,
                server = server,
            )
        }
    }
}
