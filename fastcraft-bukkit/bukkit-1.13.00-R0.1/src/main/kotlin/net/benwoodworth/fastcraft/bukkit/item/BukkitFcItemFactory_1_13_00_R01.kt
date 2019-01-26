package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import org.bukkit.inventory.ItemStack

class BukkitFcItemFactory_1_13_00_R01 : BukkitFcItemFactory {

    override fun FcItem(
        type: FcItemType,
        amount: Int,
        displayName: String?,
        lore: List<String>?
    ): FcItem {
        return BukkitFcItem_1_13_00_R01(
            ItemStack(type.bukkit.material, amount),
            displayName,
            lore
        )
    }

    override fun FcItem.copy(
        type: FcItemType,
        amount: Int,
        displayName: String?,
        lore: List<String>?
    ): FcItem {
        TODO("not implemented")
    }
}
