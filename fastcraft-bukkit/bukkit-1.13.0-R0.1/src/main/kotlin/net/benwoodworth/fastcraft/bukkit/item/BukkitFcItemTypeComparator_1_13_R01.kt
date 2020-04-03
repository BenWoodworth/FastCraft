package net.benwoodworth.fastcraft.bukkit.item

import com.google.common.base.Enums
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemType.Companion.material
import net.benwoodworth.fastcraft.platform.item.FcItemType
import org.bukkit.Material
import org.bukkit.plugin.Plugin
import javax.inject.Inject

class BukkitFcItemTypeComparator_1_13_R01 @Inject constructor(
    plugin: Plugin,
) : BukkitFcItemTypeComparator {
    private val materialIndices: Map<Material, Int> = plugin
        .getResource("bukkit/material-order.txt")
        .reader()
        .useLines { lines ->
            lines
                .filter { it.isNotBlank() }
                .mapNotNull { line -> Enums.getIfPresent(Material::class.java, line.trim()).orNull() }
                .mapIndexed { i, material -> material to i }
                .toMap()
        }

    private val comparator = compareBy<FcItemType>(
        { materialIndices[it.material] ?: Int.MAX_VALUE },
        { it.id }
    )

    override fun compare(type0: FcItemType, type1: FcItemType): Int {
        return comparator.compare(type0, type1)
    }
}