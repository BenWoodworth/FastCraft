package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItem_1_13_00_R01 @Inject constructor(
    override val base: ItemStack,
    override val displayName: FcText?,
    override val lore: List<FcText>,
    private val itemTypes: FcItemTypes
) : BukkitFcItem {

    override val type: FcItemType
        get() = itemTypes.bukkit.fromMaterial(base.type)

    override val amount: Int
        get() = base.amount
}
