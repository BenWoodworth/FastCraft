package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemTypes.Companion.fromMaterial
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItem_1_13(
    itemStack: ItemStack,
    itemTypes: FcItemTypes,
    textFactory: FcTextFactory,
) : BukkitFcItem_1_7(
    itemStack = itemStack,
    itemTypes = itemTypes,
    textFactory = textFactory
) {
    override val type: FcItemType
        get() = itemTypes.fromMaterial(itemStack.type)

    open class Factory @Inject constructor(
        itemTypes: FcItemTypes,
        textFactory: FcTextFactory,
    ) : BukkitFcItem_1_7.Factory(
        itemTypes = itemTypes,
        textFactory = textFactory
    ) {
        override fun createFcItem(itemStack: ItemStack): FcItem {
            return BukkitFcItem_1_13(
                itemStack = itemStack,
                itemTypes = itemTypes,
                textFactory = textFactory
            )
        }
    }
}
