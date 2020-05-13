package net.benwoodworth.fastcraft.bukkit.world

import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.bukkit.text.createTranslate
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.world.FcItem
import org.bukkit.Material
import org.bukkit.Server
import org.bukkit.inventory.ItemStack
import java.util.*
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

open class BukkitFcItem_1_13(
    override val material: Material,
    private val textFactory: FcText.Factory,
    private val localizer: BukkitLocalizer,
    protected val items: FcItem.Factory,
) : BukkitFcItem {
    override val id: String
        get() = material.key.toString()

    override val materialData: Nothing?
        get() = null

    private fun Material.getNameLocaleKey(prefix: String): String {
        return "$prefix.${key.namespace}.${key.key}"
    }

    override val itemName: FcText
        get() {
            var localeKey = material.getNameLocaleKey("item")

            if (localizer.localize(localeKey, Locale.ENGLISH) == null) {
                val blockKey = material.getNameLocaleKey("block")
                if (localizer.localize(blockKey, Locale.ENGLISH) != null) {
                    localeKey = blockKey
                }
            }

            return textFactory.createTranslate(localeKey)
        }

    override val blockName: FcText
        get() {
            var localeKey = material.getNameLocaleKey("block")

            if (localizer.localize(localeKey, Locale.ENGLISH) == null) {
                val itemKey = material.getNameLocaleKey("item")
                if (localizer.localize(itemKey, Locale.ENGLISH) != null) {
                    localeKey = itemKey
                }
            }

            return textFactory.createTranslate(localeKey)
        }

    override val maxAmount: Int
        get() = material.maxStackSize

    override val craftingRemainingItem: FcItem?
        get() = when (material) {
            Material.LAVA_BUCKET,
            Material.MILK_BUCKET,
            Material.WATER_BUCKET,
            -> items.fromMaterial(Material.BUCKET)

            Material.DRAGON_BREATH,
            -> items.fromMaterial(Material.GLASS_BOTTLE)

            else -> null
        }

    override fun toItemStack(amount: Int): ItemStack {
        return ItemStack(material, amount)
    }

    override fun equals(other: Any?): Boolean {
        return other is FcItem &&
                material == other.material &&
                materialData == other.materialData
    }

    override fun hashCode(): Int {
        return material.hashCode()
    }

    @Singleton
    open class Factory @Inject constructor(
        textFactory: FcText.Factory,
        items: Provider<FcItem.Factory>,
        protected val localizer: BukkitLocalizer,
        server: Server,
    ) : BukkitFcItem_1_9.Factory(
        textFactory = textFactory,
        items = items,
        server = server,
    ) {
        override val craftingTable: FcItem by lazy {
            fromMaterial(Material.CRAFTING_TABLE)
        }

        override fun fromMaterial(material: Material): FcItem {
            return BukkitFcItem_1_13(material, textFactory, localizer, items.get())
        }

        override fun fromMaterialData(materialData: Any): FcItem {
            throw UnsupportedOperationException()
        }

        override fun parseOrNull(id: String): FcItem? {
            return Material.matchMaterial(id)
                ?.let { fromMaterial(it) }
        }
    }
}
