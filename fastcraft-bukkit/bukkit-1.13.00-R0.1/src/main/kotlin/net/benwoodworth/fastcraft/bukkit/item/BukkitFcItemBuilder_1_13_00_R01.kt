package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItemBuilder_1_13_00_R01 @Inject constructor(
    private var base: ItemStack? = null,
    override var type: FcItemType,
    override var amount: Int = 1,
    override var displayName: FcText? = null,
    override var lore: MutableList<FcText> = mutableListOf()
) : BukkitFcItemBuilder {

    override fun build(): FcItem {
        return BukkitFcItem_1_13_00_R01(
            base,
            type,
            amount,
            displayName,
            lore.toList()
        )
    }
}
