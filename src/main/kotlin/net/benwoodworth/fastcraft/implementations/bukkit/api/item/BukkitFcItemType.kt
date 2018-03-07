package net.benwoodworth.fastcraft.implementations.bukkit.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.FcItemType
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.Material

class BukkitFcItemType(
        override val base: Material
) : FcItemType, Adapter<Material>()