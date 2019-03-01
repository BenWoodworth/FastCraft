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
    private val itemTypes: FcItemTypes,
    private val textFactory: FcTextFactory
) : BukkitFcItemFactory {

    override fun createFcItem(
        type: FcItemType,
        amount: Int,
        displayName: FcText?,
        lore: List<FcText>
    ): FcItem {
        val item = ItemStack(type.bukkit.material, amount)

        return BukkitFcItem_1_13_00_R01(
            base = item,
            displayName = displayName,
            lore = lore,
            itemTypes = itemTypes
        )
    }

    override fun FcItem.copy(
        type: FcItemType,
        amount: Int,
        displayName: FcText?,
        lore: List<FcText>
    ): FcItem {
        val item = bukkit.base
        item.type = type.bukkit.material
        item.amount = amount

        return BukkitFcItem_1_13_00_R01(
            base = item,
            displayName = displayName,
            lore = lore,
            itemTypes = itemTypes
        )
    }

    override fun createFcItem(itemStack: ItemStack): FcItem {
        val item = itemStack.clone()
        val meta = item.getItemMetaOrNull()

        return BukkitFcItem_1_13_00_R01(
            base = item,
            displayName = meta?.displayName
                ?.let { textFactory.createFcText(it) },
            lore = meta?.lore
                ?.map { textFactory.createFcText(it ?: "") }
                ?: emptyList(),
            itemTypes = itemTypes
        )
    }
}
