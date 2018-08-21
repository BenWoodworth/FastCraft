package net.benwoodworth.fastcraft.platform.impl.sponge.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.ItemType

class SpongeFcItemType(
        override val base: ItemType
) : FcItemType, Adapter<ItemType>()