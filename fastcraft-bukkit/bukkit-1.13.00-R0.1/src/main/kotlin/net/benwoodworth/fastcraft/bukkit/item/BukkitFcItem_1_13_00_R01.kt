package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import org.bukkit.inventory.ItemStack

class BukkitFcItem_1_13_00_R01(
    override val base: ItemStack,
    override val displayName: String?,
    override val lore: List<String>?
) : BukkitFcItem {

    override val type: FcItemType
        get() = TODO("not implemented")
    override val amount: Int
        get() = TODO("not implemented")
}
