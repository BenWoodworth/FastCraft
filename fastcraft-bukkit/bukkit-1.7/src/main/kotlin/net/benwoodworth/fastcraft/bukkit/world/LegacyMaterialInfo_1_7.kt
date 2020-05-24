package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.bukkit.text.createTranslate
import net.benwoodworth.fastcraft.bukkit.util.BukkitVersion
import net.benwoodworth.fastcraft.bukkit.util.getMap
import net.benwoodworth.fastcraft.bukkit.util.toYamlConfiguration
import net.benwoodworth.fastcraft.platform.text.FcText
import org.apache.commons.lang.WordUtils
import org.bukkit.Material
import org.bukkit.material.MaterialData
import org.bukkit.plugin.Plugin
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LegacyMaterialInfo_1_7 @Inject constructor(
    plugin: Plugin,
    bukkitVersion: BukkitVersion,
    private val textFactory: FcText.Factory,
) : LegacyMaterialInfo {
    private val itemIds: Map<Material, String>
    private val itemNames: Map<Material, String>
    private val itemVariantNames: Map<Material, List<String>>

    init {
        val version = bukkitVersion.run { "$major.$minor" }

        val yaml = plugin.getResource("bukkit/legacy-materials/$version.yml")
            ?.toYamlConfiguration()

        itemIds = yaml
            ?.getMap("item-ids") { getString(it) }
            ?.mapKeys { (key, _) -> enumValueOf(key) }
            ?: emptyMap()

        itemNames = yaml
            ?.getMap("item-names") { getString(it) }
            ?.mapKeys { (key, _) -> enumValueOf(key) }
            ?: emptyMap()

        itemVariantNames = yaml
            ?.getMap("item-variant-names") { getStringList(it) }
            ?.mapKeys { (key, _) -> enumValueOf(key) }
            ?: emptyMap()
    }

    @Suppress("DEPRECATION")
    override fun getItemName(materialData: MaterialData): FcText {
        return itemVariantNames[materialData.itemType]
            ?.getOrNull(materialData.data.toInt())
            .let { it ?: itemNames[materialData.itemType] }
            ?.let { textFactory.createTranslate("$it.name") }
            ?: run {
                var name = materialData.toString()

                // Trim data number from end
                val nameEnd = name.lastIndexOf('(')
                if (nameEnd != -1) {
                    name = name.substring(0 until nameEnd)
                }

                name = name.replace('_', ' ')

                return textFactory.create(WordUtils.capitalizeFully(name))
            }
    }

    override fun getItemId(material: Material): String {
        return itemIds[material]
            ?.let { "minecraft:$it" }
            ?: material.name
    }

    @Suppress("DEPRECATION")
    override fun getItemId(materialData: MaterialData): String {
        return if (materialData.data == 0.toByte()) {
            getItemId(materialData.itemType)
        } else {
            "${getItemId(materialData.itemType)}:${materialData.data}"
        }
    }
}
