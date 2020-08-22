package net.benwoodworth.fastcraft.bukkit.world

import com.google.common.base.Enums
import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import org.bukkit.plugin.Plugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitFcItemOrderComparator_1_13 @Inject constructor(
    plugin: Plugin,
    bukkitVersion: BukkitVersion,
    fcItemOperations: FcItem.Operations,
) : BukkitFcItemOrderComparator {
    private val materialIndices: Map<Material, Int> = plugin
        .getResource(bukkitVersion.run { "bukkit/item-order/$major.$minor.txt" })!!
        .reader()
        .useLines { lines ->
            lines
                .filter { it.isNotBlank() }
                .mapNotNull { line -> Enums.getIfPresent(Material::class.java, line.trim()).orNull() }
                .mapIndexed { i, material -> material to i }
                .toMap()
        }

    private val comparator = fcItemOperations.bukkit.run {
        compareBy<FcItem>(
            { materialIndices[it.material] ?: Int.MAX_VALUE },
            { it.id }
        )
    }

    override fun compare(item0: FcItem, item1: FcItem): Int {
        return comparator.compare(item0, item1)
    }
}
