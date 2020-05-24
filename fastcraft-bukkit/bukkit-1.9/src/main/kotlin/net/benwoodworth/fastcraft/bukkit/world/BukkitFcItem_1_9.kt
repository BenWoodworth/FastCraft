package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.material.MaterialData
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


open class BukkitFcItem_1_9(
    materialData: MaterialData,
    textFactory: FcText.Factory,
    items: FcItem.Factory,
    legacyMaterialInfo: LegacyMaterialInfo_1_7,
) : BukkitFcItem_1_7(
    materialData = materialData,
    textFactory = textFactory,
    items = items,
    legacyMaterialInfo = legacyMaterialInfo,
) {
    override val craftingRemainingItem: FcItem?
        get() = when (material) {
            Material.DRAGONS_BREATH -> items.fromMaterial(Material.GLASS_BOTTLE)
            else -> super.craftingRemainingItem
        }

    @Singleton
    open class Factory @Inject constructor(
        textFactory: FcText.Factory,
        items: Provider<FcItem.Factory>,
        server: Server,
        legacyMaterialInfo: LegacyMaterialInfo_1_7,
    ) : BukkitFcItem_1_7.Factory(
        textFactory = textFactory,
        items = items,
        server = server,
        legacyMaterialInfo = legacyMaterialInfo,
    ) {
        override fun fromMaterialData(materialData: Any): FcItem {
            require(materialData is MaterialData)
            return BukkitFcItem_1_9(materialData, textFactory, items.get(), legacyMaterialInfo)
        }
    }
}
