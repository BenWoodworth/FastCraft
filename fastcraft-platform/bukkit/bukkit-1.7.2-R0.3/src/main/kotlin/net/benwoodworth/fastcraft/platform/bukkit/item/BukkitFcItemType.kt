package net.benwoodworth.fastcraft.platform.bukkit.item

import net.benwoodworth.fastcraft.platform.api.item.FcItemType
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.Material

class BukkitFcItemType(
        override val base: Material
) : FcItemType, Adapter<Material>()