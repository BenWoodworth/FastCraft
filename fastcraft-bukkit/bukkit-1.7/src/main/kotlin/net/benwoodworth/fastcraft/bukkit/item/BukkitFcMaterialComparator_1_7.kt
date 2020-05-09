package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcMaterial
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BukkitFcMaterialComparator_1_7 @Inject constructor(
) : BukkitFcMaterialComparator {
    override fun compare(type0: FcMaterial, type1: FcMaterial): Int {
        @Suppress("DEPRECATION")
        return type0.material.id - type1.material.id
    }
}
