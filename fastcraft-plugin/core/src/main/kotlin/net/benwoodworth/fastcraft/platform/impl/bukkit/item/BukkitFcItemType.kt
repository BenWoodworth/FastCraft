package net.benwoodworth.fastcraft.platform.impl.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.Material

class BukkitFcItemType(
        override val base: Material
) : FcItemType, Adapter<Material>()