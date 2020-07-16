package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.inventory.ItemStack
import org.bukkit.material.MaterialData
import javax.inject.Inject
import javax.inject.Singleton

object BukkitFcItem_1_7 {
    @Singleton
    open class TypeClass @Inject constructor(
        private val items: FcItem.Factory,
        private val legacyMaterialInfo: LegacyMaterialInfo,
    ) : BukkitFcItem.TypeClass {
        @Suppress("DEPRECATION")
        override val FcItem.id: String
            get() = legacyMaterialInfo.getItemId(materialData)

        @Suppress("DEPRECATION")
        override val FcItem.materialData: MaterialData
            get() = when (val value = value) {
                is MaterialData -> value
                is Material -> value.getNewData(0)
                else -> error("$this is not MaterialData or Material")
            }

        @Suppress("DEPRECATION")
        override val FcItem.material: Material
            get() = when (val value = value) {
                is MaterialData -> value.itemType
                is Material -> value
                else -> error("$this is not MaterialData or Material")
            }

        override val FcItem.name: FcText
            get() = legacyMaterialInfo.getItemName(materialData)

        override val FcItem.maxAmount: Int
            get() = material.maxStackSize

        protected open fun craftingRemainingItem(item: FcItem): FcItem? {
            return when (item.material) {
                Material.LAVA_BUCKET,
                Material.MILK_BUCKET,
                Material.WATER_BUCKET,
                -> items.fromMaterial(Material.BUCKET)

                else -> null
            }
        }

        final override val FcItem.craftingRemainingItem: FcItem?
            get() = craftingRemainingItem(this)

        override fun FcItem.toItemStack(amount: Int): ItemStack {
            return materialData.toItemStack(amount)
        }
    }

    @Singleton
    open class Factory @Inject constructor(
        protected val server: Server,
    ) : BukkitFcItem.Factory {
        override val air: FcItem
            get() = fromMaterial(Material.AIR)

        override val ironSword: FcItem
            get() = fromMaterial(Material.IRON_SWORD)

        override val craftingTable: FcItem
            get() = fromMaterial(Material.WORKBENCH)

        override val anvil: FcItem
            get() = fromMaterial(Material.ANVIL)

        override val netherStar: FcItem
            get() = fromMaterial(Material.NETHER_STAR)

        @Suppress("DEPRECATION")
        override val lightGrayStainedGlassPane: FcItem
            get() = fromMaterialData(MaterialData(Material.STAINED_GLASS_PANE, 8))

        override fun fromMaterial(material: Material): FcItem {
            return FcItem(material)
        }

        override fun fromMaterialData(materialData: MaterialData): FcItem {
            return FcItem(materialData)
        }

        override fun parseOrNull(id: String): FcItem? {
            val parts = id.split(':')
            val material: Material
            val data: Byte

            fun parseMaterialOrNull(material: String): Material? {
                @Suppress("DEPRECATION")
                return Material.matchMaterial(material)
                    ?: server.unsafe.getMaterialFromInternalName(material)
                        .takeUnless { it == Material.AIR && !id.endsWith("air", true) }
            }

            when (parts.size) {
                1 -> {
                    material = parseMaterialOrNull(id) ?: return null
                    data = 0
                }
                else -> when (val parsedData = parts.last().toByteOrNull()) {
                    null -> {
                        material = parseMaterialOrNull(id) ?: return null
                        data = 0
                    }
                    else -> {
                        material = parseMaterialOrNull(parts.dropLast(1).joinToString(":")) ?: return null
                        data = parsedData
                    }
                }
            }

            @Suppress("DEPRECATION")
            return fromMaterialData(MaterialData(material, data))
        }
    }
}
