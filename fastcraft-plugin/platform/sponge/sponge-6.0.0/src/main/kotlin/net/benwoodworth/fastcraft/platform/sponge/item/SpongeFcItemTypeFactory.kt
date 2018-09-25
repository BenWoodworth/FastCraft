package net.benwoodworth.fastcraft.platform.sponge.item

import net.benwoodworth.fastcraft.platform.item.FcItemTypeFactory
import org.spongepowered.api.item.ItemTypes
import javax.inject.Inject

class SpongeFcItemTypeFactory @Inject constructor(
) : FcItemTypeFactory {

    override fun getIronSword() = SpongeFcItemType(ItemTypes.IRON_SWORD)

    override fun getCraftingTable() = SpongeFcItemType(ItemTypes.CRAFTING_TABLE)

    override fun getAnvil() = SpongeFcItemType(ItemTypes.ANVIL)

    override fun getNetherStar() = SpongeFcItemType(ItemTypes.NETHER_STAR)
}