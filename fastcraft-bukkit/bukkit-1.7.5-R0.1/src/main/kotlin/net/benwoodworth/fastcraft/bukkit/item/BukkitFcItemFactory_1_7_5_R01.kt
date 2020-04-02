package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

open class BukkitFcItemFactory_1_7_5_R01 @Inject constructor(
    protected val itemTypes: FcItemTypes,
    protected val textFactory: FcTextFactory,
) : BukkitFcItemFactory {
    override fun copyItem(item: FcItem, amount: Int): FcItem {
        if (amount == item.amount) {
            return item
        }

        val itemStack = item.toItemStack()
        itemStack.amount = amount

        return createFcItem(itemStack)
    }

    override fun createFcItem(itemStack: ItemStack): FcItem {
        return BukkitFcItem_1_7_5_R01(
            itemStack = itemStack,
            itemTypes = itemTypes,
            textFactory = textFactory
        )
    }
}
