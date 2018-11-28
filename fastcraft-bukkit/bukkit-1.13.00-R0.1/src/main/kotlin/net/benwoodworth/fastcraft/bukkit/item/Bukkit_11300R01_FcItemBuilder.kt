package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

@Suppress("ClassName")
class Bukkit_11300R01_FcItemBuilder @Inject constructor(
    private var base: ItemStack? = null,
    override var type: FcItemType,
    override var amount: Int = 1,
    override var displayName: FcText? = null,
    override var lore: MutableList<FcText> = mutableListOf()
) : FcItemBuilder {

    override fun build(): FcItem {
        return Bukkit_11300R01_FcItem(
            base,
            type,
            amount,
            displayName,
            lore.toList()
        )
    }
}
