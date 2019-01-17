package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.inventory.ItemStack

class BukkitFcItem_1_13_00_R01(
    private var base: ItemStack?,
    override var type: FcItemType,
    override var amount: Int,
    override var displayName: FcText?,
    override var lore: List<FcText>
) : BukkitFcItem {

    fun copyBase(): ItemStack? {
        return base?.clone()
    }
}
