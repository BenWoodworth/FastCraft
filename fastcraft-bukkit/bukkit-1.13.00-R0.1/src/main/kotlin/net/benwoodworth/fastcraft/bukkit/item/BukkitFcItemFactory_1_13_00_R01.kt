package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcLegacyText
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItemFactory_1_13_00_R01 @Inject constructor(
    private val itemTypes: FcItemTypes
) : BukkitFcItemFactory {

    override fun FcItem(itemStack: ItemStack): FcItem {
        return BukkitFcItem_1_13_00_R01(itemStack.clone(), itemTypes)
    }

    override fun FcItem(
        type: FcItemType,
        amount: Int,
        displayName: FcLegacyText?,
        lore: List<FcLegacyText>?
    ): FcItem {
        val itemStack = ItemStack(type.bukkit.material, amount)

        if (displayName != null || lore != null) {
            val meta = itemStack.itemMeta

            meta.displayName = displayName
            meta.lore = lore

            itemStack.itemMeta = meta
        }

        return BukkitFcItem_1_13_00_R01(itemStack, itemTypes)
    }

    override fun FcItem(
        copy: FcItem,
        type: FcItemType,
        amount: Int,
        displayName: FcLegacyText?,
        lore: List<FcLegacyText>?
    ): FcItem {
        val itemStack = copy.bukkit.toItemStack()

        itemStack.type = type.bukkit.material
        itemStack.amount = amount

        if (itemStack.hasItemMeta() || displayName != null || lore != null) {
            val meta = itemStack.itemMeta

            meta.displayName = displayName
            meta.lore = lore

            itemStack.itemMeta = meta
        }

        return BukkitFcItem_1_13_00_R01(itemStack, itemTypes)
    }
}
