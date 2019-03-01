package net.benwoodworth.fastcraft.bukkit.item

import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItemConverter_1_13_00_R01 @Inject constructor(
) : BukkitFcItemConverter {

    override fun BukkitFcItem.toItemStack(): ItemStack {
        return itemStack.clone()
    }
}