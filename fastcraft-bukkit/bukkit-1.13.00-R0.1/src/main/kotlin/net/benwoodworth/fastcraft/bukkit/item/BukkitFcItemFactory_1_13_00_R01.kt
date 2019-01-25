package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.bukkit
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import org.bukkit.inventory.ItemStack
import javax.inject.Inject
import javax.inject.Provider

class BukkitFcItemFactory_1_13_00_R01 @Inject constructor(
    private val itemTypes: FcItemTypes,
    private val buildText: Provider<FcTextBuilder>
) : BukkitFcItemFactory {

    override fun FcItem(
        type: FcItemType,
        amount: Int,
        displayName: FcText?,
        lore: List<FcText>?
    ): FcItem {
        return BukkitFcItem_1_13_00_R01(
            ItemStack(type.bukkit.material, amount),
            displayName,
            lore
        )
    }

    override fun FcItem.copy(
        type: FcItemType,
        amount: Int,
        displayName: FcText?,
        lore: List<FcText>?
    ): FcItem {
        TODO("not implemented")
    }
}
