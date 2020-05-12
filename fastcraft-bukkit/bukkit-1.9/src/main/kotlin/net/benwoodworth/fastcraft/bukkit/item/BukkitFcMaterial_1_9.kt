package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcMaterial
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.material.MaterialData
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


open class BukkitFcMaterial_1_9(
    materialData: MaterialData,
    textFactory: FcText.Factory,
    materials: FcMaterial.Factory,
) : BukkitFcMaterial_1_7(
    materialData = materialData,
    textFactory = textFactory,
    materials = materials,
) {
    override val craftingRemainingItem: FcMaterial?
        get() = when (material) {
            Material.DRAGONS_BREATH -> materials.fromMaterial(Material.GLASS_BOTTLE)
            else -> super.craftingRemainingItem
        }

    @Singleton
    open class Factory @Inject constructor(
        textFactory: FcText.Factory,
        materials: Provider<FcMaterial.Factory>,
        server: Server,
    ) : BukkitFcMaterial_1_7.Factory(
        textFactory = textFactory,
        materials = materials,
        server = server,
    ) {
        override fun fromMaterialData(materialData: Any): FcMaterial {
            require(materialData is MaterialData)
            return BukkitFcMaterial_1_9(materialData, textFactory, materials.get())
        }
    }
}
