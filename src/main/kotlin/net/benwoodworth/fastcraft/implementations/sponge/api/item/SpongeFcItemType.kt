package net.benwoodworth.fastcraft.implementations.sponge.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.FcItemType
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.ItemType as Sponge_ItemType

class SpongeFcItemType(
        base: Sponge_ItemType
) : FcItemType, Adapter<Sponge_ItemType>(base)