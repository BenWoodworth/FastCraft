package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItemFactory_1_13_00_R01 @Inject constructor(
    private val itemFactory: BukkitFcItem_1_13_00_R01Factory
) : BukkitFcItemFactory {

    override fun createFcItem(itemStack: ItemStack): FcItem {
        return itemFactory.create(itemStack)
    }
}
