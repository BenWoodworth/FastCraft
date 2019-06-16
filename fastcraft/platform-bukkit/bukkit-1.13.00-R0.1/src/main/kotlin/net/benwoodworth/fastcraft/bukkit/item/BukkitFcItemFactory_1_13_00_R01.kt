package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItemFactory_1_13_00_R01 @Inject constructor(
    private val itemFactory: BukkitFcItem_1_13_00_R01Factory
) : BukkitFcItemFactory {

    override fun createFcItem(itemStack: ItemStack): FcItem {
        return itemFactory.create(itemStack)
    }
}
