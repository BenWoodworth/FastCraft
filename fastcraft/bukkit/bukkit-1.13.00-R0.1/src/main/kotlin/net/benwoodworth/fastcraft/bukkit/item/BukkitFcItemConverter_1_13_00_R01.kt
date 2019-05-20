package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.BukkitFcTextConverter
import net.benwoodworth.fastcraft.platform.text.FcLocale
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

class BukkitFcItemConverter_1_13_00_R01 @Inject constructor(
    private val textConverter: BukkitFcTextConverter
) : BukkitFcItemConverter {

    override fun BukkitFcItem.toItemStack(locale: FcLocale): ItemStack {
        val itemStack = base.clone()
        val meta = itemStack.itemMeta

        with(textConverter) {

            meta.displayName = displayName?.toLegacy(locale)

            meta.lore = lore
                .takeIf { it.any() }
                ?.map { it.toLegacy(locale) }
        }

        itemStack.itemMeta = meta
        return itemStack
    }
}