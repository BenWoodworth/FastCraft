package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import org.apache.commons.lang.WordUtils
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.inventory.ItemStack
import org.bukkit.material.MaterialData
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton


open class BukkitFcItem_1_7(
    override val materialData: MaterialData,
    protected val textFactory: FcText.Factory,
    protected val items: FcItem.Factory,
) : BukkitFcItem {
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

    override val craftingRemainingItem: FcItem?
        get() = when (material) {
            Material.LAVA_BUCKET,
            Material.MILK_BUCKET,
            Material.WATER_BUCKET,
            -> items.fromMaterial(Material.BUCKET)

            else -> null
        }

    override fun toItemStack(amount: Int): ItemStack {
        return materialData.toItemStack()
    }

    override fun equals(other: Any?): Boolean {
        return other is FcItem && materialData == other.materialData
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
        protected val items: Provider<FcItem.Factory>,
        protected val server: Server,
    ) : BukkitFcItem.Factory {
        override val air: FcItem by lazy { fromMaterial(Material.AIR) }
        override val ironSword: FcItem by lazy { fromMaterial(Material.IRON_SWORD) }
        override val craftingTable: FcItem by lazy { fromMaterial(Material.WORKBENCH) }
        override val anvil: FcItem by lazy { fromMaterial(Material.ANVIL) }
        override val netherStar: FcItem by lazy { fromMaterial(Material.NETHER_STAR) }

        override fun fromMaterial(material: Material): FcItem {
            return fromMaterialData(MaterialData(material))
        }

        override fun fromMaterialData(materialData: Any): FcItem {
            require(materialData is MaterialData)
            return BukkitFcItem_1_7(materialData, textFactory, items.get())
        }

        override fun parseOrNull(id: String): FcItem? {
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
