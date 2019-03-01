package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcLegacyText
import net.benwoodworth.fastcraft.platform.text.FcLegacyTextFactory
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItem_1_13_00_R01 @Inject constructor(
    override val itemStack: ItemStack,
    private val itemTypes: FcItemTypes,
    private val legacyTextFactory: FcLegacyTextFactory
) : BukkitFcItem {

    override val type: FcItemType
        get() = itemTypes.bukkit.fromMaterial(itemStack.type)

    override val amount: Int
        get() = itemStack.amount

    override val displayName: FcLegacyText?
        get() = with(legacyTextFactory.bukkit) {
            itemStack.itemMeta?.displayName?.let { createFcLegacyText(it) }
        }

    override val lore: List<FcLegacyText>?
        get() = with(legacyTextFactory.bukkit) {
            itemStack.itemMeta?.lore?.map { createFcLegacyText(it) }
        }
}
