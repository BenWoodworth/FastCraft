package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.platform.item.FcMaterial
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.Material
import org.bukkit.Server
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

open class BukkitFcMaterial_1_15(
    material: Material,
    textFactory: FcText.Factory,
    localizer: BukkitLocalizer,
    materials: FcMaterial.Factory,
) : BukkitFcMaterial_1_13(
    material = material,
    textFactory = textFactory,
    localizer = localizer,
    materials = materials,
) {
    override val craftingRemainingItem: FcMaterial?
        get() = when (material) {
            Material.HONEY_BOTTLE -> materials.fromMaterial(Material.GLASS_BOTTLE)
            else -> super.craftingRemainingItem
        }

    @Singleton
    class Factory @Inject constructor(
        textFactory: FcText.Factory,
        localizer: BukkitLocalizer,
        materials: Provider<FcMaterial.Factory>,
        server: Server,
    ) : BukkitFcMaterial_1_13.Factory(
        textFactory = textFactory,
        localizer = localizer,
        materials = materials,
        server = server,
    ) {
        override fun fromMaterial(material: Material): FcMaterial {
            return BukkitFcMaterial_1_15(material, textFactory, localizer, materials.get())
        }
    }
}
