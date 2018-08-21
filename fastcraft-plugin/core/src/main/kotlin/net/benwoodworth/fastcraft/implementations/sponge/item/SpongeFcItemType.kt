package net.benwoodworth.fastcraft.implementations.sponge.item

import net.benwoodworth.fastcraft.dependencies.item.FcItemType
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.ItemType

class SpongeFcItemType(
        override val base: ItemType
) : FcItemType, Adapter<ItemType>()