package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.inventory.ItemStack

class BukkitFcItem_1_13_00_R01(
    private var base: ItemStack?,
    override var type: FcItemType,
    override var amount: Int,
    override var displayName: FcText?,
    override var lore: List<FcText>
) : BukkitFcItem {

    override fun toItemStack(item: FcItem, locale: FcLocale): ItemStack {
        TODO("not implemented")
    }
}
