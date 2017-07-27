package net.benwoodworth.fastcraft.impl.sponge.item

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.impl.sponge.text.SpongeText
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [Item].
 */
class SpongeItem(
        /**
         * The item being adapted. Should not be modified.
         */
        private val baseItem: ItemStack
) : Item by SpongeItem.Mutable(baseItem) {

    class Mutable(
            baseItem: ItemStack
    ) : Item.Mutable, Adapter<ItemStack>(baseItem) {

        override var amount: Int
            get() = base.quantity
            set(value) {
                base.quantity = value
            }

        override val name: Text
            get() = SpongeText(Sponge_Text.of(base))

        override var displayName: Text?
            get() {
                val displayName = base.get(Keys.DISPLAY_NAME).orElse(null)
                return SpongeText(displayName)
            }
            set(value) {
                value as SpongeText
                base.offer(Keys.DISPLAY_NAME, value.base)
            }

        override var lore: List<Text?>?
            get() {
                val lore = base.get(Keys.ITEM_LORE).orElse(null)
                return lore?.map {
                    it?.run { SpongeText(it) }
                }
            }
            set(value) {
                base.offer(Keys.ITEM_LORE, value?.map {
                    it?.let { (it as SpongeText).base }
                })
            }

        override val maxStackSize: Int
            get() = base.maxStackQuantity

        override fun isSimilar(item: Item): Boolean {
            if (item !is SpongeItem) {
                return false
            }

            var other = item.baseItem
            if (other.quantity != base.quantity) {
                other = other.copy()
                other.quantity = base.quantity
            }
            return base.equalTo(other)
        }

        override fun toMutable(): Item.Mutable {
            return SpongeItem.Mutable(base.copy())
        }

        override fun toImmutable(): Item {
            return SpongeItem(base.copy())
        }
    }
}
