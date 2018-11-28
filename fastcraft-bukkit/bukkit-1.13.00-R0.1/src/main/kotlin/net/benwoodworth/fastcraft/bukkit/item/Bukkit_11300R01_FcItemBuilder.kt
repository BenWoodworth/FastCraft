package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

@Suppress("ClassName")
class Bukkit_11300R01_FcItemBuilder @Inject constructor(
    itemTypes: FcItemTypes
) : FcItemBuilder {

    private var copyFrom: ItemStack? = null

    override var type: FcItemType = itemTypes.air
    override var amount: Int = 1
    override var displayName: FcText? = null
    override var lore: MutableList<FcText> = mutableListOf()

    fun copyFrom(itemStack: ItemStack) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun build(): FcItem {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
