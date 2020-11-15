package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.Material
import org.bukkit.material.MaterialData

interface LegacyMaterialInfo {
    fun getItemName(materialData: MaterialData): FcText

    fun getItemId(material: Material): String

    fun getItemId(materialData: MaterialData): String
}
