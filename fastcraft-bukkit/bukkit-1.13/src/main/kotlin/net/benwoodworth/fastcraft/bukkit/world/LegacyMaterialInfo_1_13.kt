@file:Suppress("DEPRECATION")

package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.Material
import org.bukkit.material.MaterialData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LegacyMaterialInfo_1_13 @Inject constructor(
) : LegacyMaterialInfo {
    override fun getItemName(materialData: MaterialData): FcText {
        error("Legacy material info not supported")
    }

    override fun getItemId(material: Material): String {
        error("Legacy material info not supported")
    }

    override fun getItemId(materialData: MaterialData): String {
        error("Legacy material info not supported")
    }
}
