package net.benwoodworth.fastcraft.implementations.sponge.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.FcItemTypeFactory
import javax.inject.Inject
import org.spongepowered.api.item.ItemTypes as Sponge_ItemTypes

class SpongeFcItemTypeFactory @Inject constructor(
) : FcItemTypeFactory {

    override fun getIronSword() = SpongeFcItemType(Sponge_ItemTypes.IRON_SWORD)

    override fun getCraftingTable() = SpongeFcItemType(Sponge_ItemTypes.CRAFTING_TABLE)

    override fun getAnvil() = SpongeFcItemType(Sponge_ItemTypes.ANVIL)

    override fun getNetherStar() = SpongeFcItemType(Sponge_ItemTypes.NETHER_STAR)
}