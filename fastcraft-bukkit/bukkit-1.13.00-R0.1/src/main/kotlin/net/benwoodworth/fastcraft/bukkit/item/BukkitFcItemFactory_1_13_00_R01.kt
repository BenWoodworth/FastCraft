package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItemFactory_1_13_00_R01 @Inject constructor(
    private val itemTypes: FcItemTypes
) : BukkitFcItemFactory {

    override fun FcItem(itemStack: ItemStack): FcItem {
        return BukkitFcItem_1_13_00_R01(itemStack, itemTypes)
    }

    override fun FcItem(
        type: FcItemType,
        amount: Int,
        displayName: String?,
        lore: List<String>?
    ): FcItem {
        val itemStack = ItemStack(type.bukkit.material, amount)

        if (displayName != null || lore != null) {
            val meta = itemStack.itemMeta

            meta.displayName = displayName
            meta.lore = lore

            itemStack.itemMeta = meta
        }

        return FcItem(itemStack)
    }

    override fun FcItem.copy(
        type: FcItemType,
        amount: Int,
        displayName: String?,
        lore: List<String>?
    ): FcItem {
        val itemStack = this.bukkit.cloneItemStack()

        itemStack.type = type.bukkit.material
        itemStack.amount = amount

        if (itemStack.hasItemMeta() || displayName != null || lore != null) {
            val meta = itemStack.itemMeta

            meta.displayName = displayName
            meta.lore = lore

            itemStack.itemMeta = meta
        }

        return FcItem(itemStack)
    }
}
