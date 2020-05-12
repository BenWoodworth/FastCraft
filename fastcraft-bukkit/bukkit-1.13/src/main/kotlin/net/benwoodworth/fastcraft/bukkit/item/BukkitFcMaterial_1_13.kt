package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.BukkitLocalizer
import net.benwoodworth.fastcraft.bukkit.text.createTranslate
import net.benwoodworth.fastcraft.platform.item.FcMaterial
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.Material
import org.bukkit.Server
import java.util.*
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

open class BukkitFcMaterial_1_13(
    override val material: Material,
    private val textFactory: FcText.Factory,
    private val localizer: BukkitLocalizer,
    protected val materials: FcMaterial.Factory,
) : BukkitFcMaterial {
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

    override val craftingResult: FcMaterial?
        get() = when (material) {
            Material.LAVA_BUCKET,
            Material.MILK_BUCKET,
            Material.WATER_BUCKET,
            -> materials.fromMaterial(Material.BUCKET)

            Material.DRAGON_BREATH,
            -> materials.fromMaterial(Material.GLASS_BOTTLE)

            else -> null
        }

    override fun equals(other: Any?): Boolean {
        return other is FcMaterial &&
                material == other.material &&
                materialData == other.materialData
    }

    override fun hashCode(): Int {
        return material.hashCode()
    }

    @Singleton
    open class Factory @Inject constructor(
        textFactory: FcText.Factory,
        materials: Provider<FcMaterial.Factory>,
        protected val localizer: BukkitLocalizer,
        server: Server,
    ) : BukkitFcMaterial_1_9.Factory(
        textFactory = textFactory,
        materials = materials,
        server = server,
    ) {
        override val craftingTable: FcMaterial by lazy {
            fromMaterial(Material.CRAFTING_TABLE)
        }

        override fun fromMaterial(material: Material): FcMaterial {
            return BukkitFcMaterial_1_13(material, textFactory, localizer, materials.get())
        }

        override fun fromMaterialData(materialData: Any): FcMaterial {
            throw UnsupportedOperationException()
        }

        override fun parseOrNull(id: String): FcMaterial? {
            return Material.matchMaterial(id)
                ?.let { fromMaterial(it) }
        }
    }
}
