package net.benwoodworth.fastcraft.bukkit.bukkit_1_13_R01.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

@Suppress("ClassName")
class BukkitFcItemBuilder @Inject constructor(
    private var base: ItemStack? = null,
    override var type: FcItemType,
    override var amount: Int = 1,
    override var displayName: FcText? = null,
    override var lore: MutableList<FcText> = mutableListOf()
) : FcItemBuilder {

    override fun build(): FcItem {
        return BukkitFcItem(
            base,
            type,
            amount,
            displayName,
            lore.toList()
        )
    }
}
