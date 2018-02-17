package net.benwoodworth.fastcraft.implementations.bukkit.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.ItemType
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.Material

class BukkitItemType(
        base: Material
) : ItemType, Adapter<Material>(base)