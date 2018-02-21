package net.benwoodworth.fastcraft.implementations.bukkit.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemType
import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.implementations.bukkit.api.text.BukkitFcText
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.inventory.ItemStack

/**
 * Bukkit implementation of [FcItem].
 *
 * @property baseItem The item being adapter. Should not be modified.
 */
class BukkitFcItem(
        private val baseItem: ItemStack
) : FcItem by BukkitFcItem.Mutable(baseItem) {

    class Mutable(
            baseItem: ItemStack
    ) : FcItem.Mutable, Adapter<ItemStack>(baseItem) {

        override val type: FcItemType
            get() = BukkitFcItemType(base.type)

        override var amount: Int
            get() = base.amount
            set(value) {
                base.amount = value
            }

        override val name: FcText
            get() = BukkitFcText(base.type.name)

        override var displayName: FcText?
            get() = base.itemMeta.displayName?.let(::BukkitFcText)
            set(value) {
                base.itemMeta = base.itemMeta?.apply {
                    displayName = (value as BukkitFcText?)?.text
                }
            }

        override var lore: List<FcText?>?
            get() = base.itemMeta
                    .takeIf { it.hasLore() }?.lore
                    ?.map { it?.let(::BukkitFcText) }
            set(value) {
                base.itemMeta = base.itemMeta.apply {
                    lore = value?.map { (it as BukkitFcText?)?.text }
                }
            }

        override val maxStackSize: Int
            get() = base.maxStackSize

        override var durability: Int
            get() = base.durability.toInt()
            set(value) {
                base.durability = value.toShort()
            }

        override fun isSimilar(item: FcItem): Boolean {
            return base.isSimilar((item as BukkitFcItem).baseItem)
        }

        override fun mutableCopy(): FcItem.Mutable {
            return BukkitFcItem.Mutable(base.clone())
        }

        override fun immutableCopy(): FcItem {
            return BukkitFcItem(base.clone())
        }
    }
}
