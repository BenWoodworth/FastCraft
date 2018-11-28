package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.Bukkit_11300R01_FcText
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemBuilder
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.locale.FcLocale
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import net.benwoodworth.fastcraft.util.getAs
import net.benwoodworth.fastcraft.util.invoke
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import javax.inject.Inject
import javax.inject.Provider

@Suppress("ClassName")
class Bukkit_11300R01_FcItemFactory @Inject constructor(
    private val itemTypes: FcItemTypes,
    private val buildText: Provider<FcTextBuilder>
) : FcItemFactory {

    override fun buildItem(copy: FcItem?, build: FcItemBuilder.() -> Unit): FcItem {
        val builder = when (copy) {
            null -> Bukkit_11300R01_FcItemBuilder(
                base = null,
                type = itemTypes.air,
                amount = 1,
                displayName = null,
                lore = mutableListOf()
            )
            else -> Bukkit_11300R01_FcItemBuilder(
                base = copy
                    .getAs<Bukkit_11300R01_FcItem>()
                    .copyBase(),
                type = copy.type,
                amount = copy.amount,
                displayName = copy.displayName,
                lore = copy.lore.toMutableList()
            )
        }

        return builder
            .apply { build(this) }
            .build()
    }

    fun fromItemStack(itemStack: ItemStack): FcItem {
        // TODO decode
        val displayName = itemStack.displayName
            ?.let { displayName ->
                buildText { text = displayName }
            }

        // TODO decode
        val lore = itemStack.lore
            ?.let { lore ->
                lore.map { line ->
                    buildText { text = line }
                }
            }
            ?: emptyList()

        return Bukkit_11300R01_FcItem(
            base = itemStack,
            amount = itemStack.amount,
            type = Bukkit_11300R01_FcItemType(itemStack.type),
            displayName = displayName,
            lore = lore
        )
    }

    fun toItemStack(item: FcItem, locale: FcLocale): ItemStack {
        val result = item
            .getAs<Bukkit_11300R01_FcItem>()
            .copyBase()
            ?: ItemStack(Material.AIR)

        result.type = item.type
            .getAs<Bukkit_11300R01_FcItemType>()
            .material

        result.amount = item.amount

        result.displayName = item.displayName
            ?.getAs<Bukkit_11300R01_FcText>()
            ?.toLegacy(locale)

        result.lore = item.lore
            .takeIf { it.any() }
            ?.map { line ->
                line
                    .getAs<Bukkit_11300R01_FcText>()
                    .toLegacy(locale)
            }

        return result
    }
}
