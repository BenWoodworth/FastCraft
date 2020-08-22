package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.bukkit.text.createTranslate
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.inventory.ItemStack
import org.bukkit.material.MaterialData
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

object BukkitFcItem_1_13 {
    @Singleton
    open class Operations @Inject constructor(
        private val fcTextFactory: FcText.Factory,
        private val localizer: BukkitLocalizer,
        private val fcItemFactory: FcItem.Factory,
    ) : BukkitFcItem.Operations {
        override val FcItem.id: String
            get() = material.key.toString()

        override val FcItem.material: Material
            get() = value as Material

        @Suppress("DEPRECATION")
        override val FcItem.materialData: MaterialData
            get() = error("MaterialData not supported")

        private fun Material.getNameLocaleKey(prefix: String): String {
            return "$prefix.${key.namespace}.${key.key}"
        }

        override val FcItem.name: FcText
            get() {
                var localeKey = material.getNameLocaleKey("item")

                if (localizer.localize(localeKey, Locale.ENGLISH) == null) {
                    val blockKey = material.getNameLocaleKey("block")
                    if (localizer.localize(blockKey, Locale.ENGLISH) != null) {
                        localeKey = blockKey
                    }
                }

                return fcTextFactory.createTranslate(localeKey)
            }

        override val FcItem.maxAmount: Int
            get() = material.maxStackSize

        override val FcItem.craftingRemainingItem: FcItem?
            get() = when (material) {
                Material.LAVA_BUCKET,
                Material.MILK_BUCKET,
                Material.WATER_BUCKET,
                -> fcItemFactory.fromMaterial(Material.BUCKET)

                Material.DRAGON_BREATH,
                -> fcItemFactory.fromMaterial(Material.GLASS_BOTTLE)

                else -> null
            }

        override fun FcItem.toItemStack(amount: Int): ItemStack {
            return ItemStack(material, amount)
        }
    }

    @Singleton
    open class Factory @Inject constructor(
        server: Server,
    ) : BukkitFcItem_1_7.Factory(
        server = server,
    ) {
        override val craftingTable: FcItem
            get() = fromMaterial(Material.CRAFTING_TABLE)

        override val lightGrayStainedGlassPane: FcItem
            get() = fromMaterial(Material.LIGHT_GRAY_STAINED_GLASS_PANE)

        @Suppress("DEPRECATION")
        override fun fromMaterialData(materialData: MaterialData): FcItem {
            error("MaterialData is not supported")
        }

        override fun parseOrNull(id: String): FcItem? {
            return Material.matchMaterial(id)
                ?.let { fromMaterial(it) }
        }
    }
}
