package net.benwoodworth.fastcraft.bukkit.item

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.ItemStack

@AutoFactory
class BukkitFcItem_1_13_R01(
    itemStack: ItemStack,
    @Provided itemTypes: FcItemTypes,
    @Provided textFactory: FcTextFactory,
) : BukkitFcItem_1_7_5_R01(
    itemStack = itemStack,
    itemTypes = itemTypes,
    textFactory = textFactory
) {
    override val type: FcItemType
        get() = itemTypes.fromMaterial(itemStack.type)
}
