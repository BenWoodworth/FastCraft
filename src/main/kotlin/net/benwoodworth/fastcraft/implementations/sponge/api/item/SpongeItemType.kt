package net.benwoodworth.fastcraft.implementations.sponge.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.ItemType
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.item.ItemType as Sponge_ItemType

class SpongeItemType(
        base: Sponge_ItemType
) : ItemType, Adapter<Sponge_ItemType>(base)