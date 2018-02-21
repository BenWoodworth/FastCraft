package net.benwoodworth.fastcraft.implementations.sponge.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.FcItemType
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.ItemType

class SpongeFcItemType(
        base: ItemType
) : FcItemType, Adapter<ItemType>(base)