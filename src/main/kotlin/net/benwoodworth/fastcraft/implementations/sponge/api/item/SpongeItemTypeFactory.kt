package net.benwoodworth.fastcraft.implementations.sponge.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.ItemTypeFactory
import javax.inject.Inject
import org.spongepowered.api.item.ItemTypes as Sponge_ItemTypes

class SpongeItemTypeFactory @Inject constructor(
) : ItemTypeFactory {

    override fun getIronSword() = SpongeItemType(Sponge_ItemTypes.IRON_SWORD)

    override fun getCraftingTable() = SpongeItemType(Sponge_ItemTypes.CRAFTING_TABLE)

    override fun getAnvil() = SpongeItemType(Sponge_ItemTypes.ANVIL)

    override fun getNetherStar() = SpongeItemType(Sponge_ItemTypes.NETHER_STAR)
}