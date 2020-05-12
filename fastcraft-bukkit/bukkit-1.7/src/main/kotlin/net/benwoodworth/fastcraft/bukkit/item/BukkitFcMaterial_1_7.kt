package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcMaterial
import net.benwoodworth.fastcraft.platform.text.FcText
import org.apache.commons.lang.WordUtils
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.material.MaterialData
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


open class BukkitFcMaterial_1_7(
    override val materialData: MaterialData,
    protected val textFactory: FcText.Factory,
    protected val materials: FcMaterial.Factory,
) : BukkitFcMaterial {
    @Suppress("DEPRECATION")
    override val id: String
        get() = if (materialData.data == 0.toByte()) {
            materialData.itemType.name
        } else {
            "${materialData.itemType.name}:${materialData.data}"
        }

    override val material: Material
        get() = materialData.itemType

    override val itemName: FcText
        get() = textFactory.createLegacy(materialData.getName())

    override val blockName: FcText
        get() = itemName

    override val maxAmount: Int
        get() = material.maxStackSize

    override val craftingRemainingItem: FcMaterial?
        get() = when (material) {
            Material.LAVA_BUCKET,
            Material.MILK_BUCKET,
            Material.WATER_BUCKET,
            -> materials.fromMaterial(Material.BUCKET)

            else -> null
        }

    override fun equals(other: Any?): Boolean {
        return other is FcMaterial && materialData == other.materialData
    }

    override fun hashCode(): Int {
        return materialData.hashCode()
    }

    private fun MaterialData.getName(): String {
        var name = toString()

        // Trim data number from end
        val nameEnd = name.lastIndexOf('(')
        if (nameEnd != -1) {
            name = name.substring(0 until nameEnd)
        }

        name = name.replace('_', ' ')

        return WordUtils.capitalizeFully(name)
    }

    @Singleton
    open class Factory @Inject constructor(
        protected val textFactory: FcText.Factory,
        protected val materials: Provider<FcMaterial.Factory>,
        protected val server: Server,
    ) : BukkitFcMaterial.Factory {
        override val air: FcMaterial by lazy { fromMaterial(Material.AIR) }
        override val ironSword: FcMaterial by lazy { fromMaterial(Material.IRON_SWORD) }
        override val craftingTable: FcMaterial by lazy { fromMaterial(Material.WORKBENCH) }
        override val anvil: FcMaterial by lazy { fromMaterial(Material.ANVIL) }
        override val netherStar: FcMaterial by lazy { fromMaterial(Material.NETHER_STAR) }

        override fun fromMaterial(material: Material): FcMaterial {
            return fromMaterialData(MaterialData(material))
        }

        override fun fromMaterialData(materialData: Any): FcMaterial {
            require(materialData is MaterialData)
            return BukkitFcMaterial_1_7(materialData, textFactory, materials.get())
        }

        override fun parseOrNull(id: String): FcMaterial? {
            val parts = id.split(':')
            val material: Material
            val data: Byte

            fun parseMaterialOrNull(material: String): Material? {
                @Suppress("DEPRECATION")
                return Material.matchMaterial(material)
                    ?: server.unsafe.getMaterialFromInternalName(material)
            }

            when (parts.size) {
                1 -> {
                    material = parseMaterialOrNull(id) ?: return null
                    data = 0
                }
                else -> when (val parsedData = parts.last().toByteOrNull()) {
                    null -> {
                        material = parseMaterialOrNull(parts.dropLast(1).joinToString(":")) ?: return null
                        data = 0
                    }
                    else -> {
                        material = parseMaterialOrNull(id) ?: return null
                        data = parsedData
                    }
                }
            }

            @Suppress("DEPRECATION")
            return fromMaterialData(MaterialData(material, data))
        }
    }
}
