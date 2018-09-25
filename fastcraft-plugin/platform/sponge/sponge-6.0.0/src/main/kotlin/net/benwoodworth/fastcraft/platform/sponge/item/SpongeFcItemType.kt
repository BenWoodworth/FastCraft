package net.benwoodworth.fastcraft.platform.sponge.item

import net.benwoodworth.fastcraft.platform.api.item.FcItemType
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.ItemType

class SpongeFcItemType(
        override val base: ItemType
) : FcItemType, Adapter<ItemType>()