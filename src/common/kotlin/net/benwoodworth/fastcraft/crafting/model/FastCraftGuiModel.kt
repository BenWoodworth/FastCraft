package net.benwoodworth.fastcraft.crafting.model

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.player.FcPlayer
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import javax.inject.Provider

@AutoFactory
class FastCraftGuiModel(
    val player: FcPlayer,
    @Provided private val itemTypes: FcItemTypes,
    @Provided private val textFactory: FcTextFactory,
    @Provided private val recipeFinder: CraftableRecipeFinder,
    @Provided private val itemAmountsProvider: Provider<ItemAmounts>,
    @Provided private val itemFactory: FcItemFactory
) {
    var craftAmount: Int? = null

    var recipes: List<FastCraftRecipe> = emptyList()

    val inventoryItemAmounts: ItemAmounts = itemAmountsProvider.get()

    fun updateInventoryItemAmounts() {
        inventoryItemAmounts.clear()

        player.inventory.storage.forEach { slot ->
            slot.item?.let { item -> inventoryItemAmounts += item }
        }
    }
}
