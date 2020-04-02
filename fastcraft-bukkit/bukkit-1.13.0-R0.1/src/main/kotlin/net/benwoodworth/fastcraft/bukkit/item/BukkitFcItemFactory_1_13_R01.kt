package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

open class BukkitFcItemFactory_1_13_R01 @Inject constructor(
    itemTypes: FcItemTypes,
    textFactory: FcTextFactory,
) : BukkitFcItemFactory_1_7_5_R01(
    itemTypes = itemTypes,
    textFactory = textFactory
) {
    override fun createFcItem(itemStack: ItemStack): FcItem {
        return BukkitFcItem_1_13_R01(
            itemStack = itemStack,
            itemTypes = itemTypes,
            textFactory = textFactory
        )
    }
}
