package net.benwoodworth.fastcraft.bukkit.world

import com.google.common.base.Enums
import net.benwoodworth.fastcraft.platform.world.FcMaterial
import org.bukkit.Material
import org.bukkit.plugin.Plugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitFcMaterialComparator_1_13 @Inject constructor(
    plugin: Plugin,
) : BukkitFcMaterialComparator {
    private val materialIndices: Map<Material, Int> = plugin
        .getResource("bukkit/material-order.txt")!!
        .reader()
        .useLines { lines ->
            lines
                .filter { it.isNotBlank() }
                .mapNotNull { line -> Enums.getIfPresent(Material::class.java, line.trim()).orNull() }
                .mapIndexed { i, material -> material to i }
                .toMap()
        }

    private val comparator = compareBy<FcMaterial>(
        { materialIndices[it.material] ?: Int.MAX_VALUE },
        { it.id }
    )

    override fun compare(type0: FcMaterial, type1: FcMaterial): Int {
        return comparator.compare(type0, type1)
    }
}
