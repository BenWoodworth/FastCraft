package net.benwoodworth.fastcraft.impl.bukkit.item

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.impl.bukkit.text.BukkitText
import net.benwoodworth.fastcraft.util.Adapter
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * Bukkit implementation of [Item].
 */
class BukkitItem(
        /**
         * The item being adapter. Should not be modified.
         */
        private val baseItem: ItemStack
) : Item by BukkitItem.Mutable(baseItem) {

    class Mutable(
            baseItem: ItemStack
    ) : Item.Mutable, Adapter<ItemStack>(baseItem) {

        override var amount: Int
            get() = base.amount
            set(value) {
                base.amount = value
            }

        override val name: Text
            get() = BukkitText(base.type.name)

        override var displayName: Text?
            get() = base.itemMeta.displayName?.let(::BukkitText)
            set(value) {
                base.itemMeta = base.itemMeta?.apply {
                    displayName = (value as BukkitText?)?.text
                }
            }

        override var lore: List<Text?>?
            get() = base.itemMeta
                    .takeIf { it.hasLore() }?.lore
                    ?.map { it?.let(::BukkitText) }
            set(value) {
                base.itemMeta = base.itemMeta.apply {
                    lore = value?.map { (it as BukkitText?)?.text }
                }
            }

        override val maxStackSize: Int
            get() = base.maxStackSize

        override fun isSimilar(item: Item): Boolean {
            return base.isSimilar((item as BukkitItem).baseItem)
        }

        override fun mutableCopy(): Item.Mutable {
            return BukkitItem.Mutable(base.clone())
        }

        override fun immutableCopy(): Item {
            return BukkitItem(base.clone())
        }
    }

    class Builder : Item.Builder {

        private var result = ItemStack(Material.AIR)

        override fun build() = BukkitItem(result.clone())

        override fun from(item: Item) = also {
            result = (item.mutableCopy() as BukkitItem.Mutable).base
        }

        override fun type(typeId: String) = also {
            @Suppress("DEPRECATION")
            result.type = Bukkit.getUnsafe().getMaterialFromInternalName(typeId)
        }

        override fun amount(amount: Int) = also {
            result.amount = amount
        }

        override fun displayName(displayName: Text?) = also {
            result.itemMeta = result.itemMeta.apply {
                setDisplayName((displayName as BukkitText?)?.text)
            }
        }

        override fun lore(vararg lore: Text?) = also {
            result.itemMeta = result.itemMeta.apply {
                this.lore = lore.map { (it as BukkitText?)?.text }
            }
        }
    }
}
