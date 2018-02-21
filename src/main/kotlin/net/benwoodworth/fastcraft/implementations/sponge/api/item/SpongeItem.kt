package net.benwoodworth.fastcraft.implementations.sponge.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.Item
import net.benwoodworth.fastcraft.dependencies.api.item.ItemType
import net.benwoodworth.fastcraft.dependencies.api.text.Text
import net.benwoodworth.fastcraft.implementations.sponge.api.text.SpongeText
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Sponge implementation of [Item].
 *
 * @property baseItem The item being adapted. Should not be modified.
 */
class SpongeItem(
        private val baseItem: ItemStack
) : Item by SpongeItem.Mutable(baseItem) {

    class Mutable(
            baseItem: ItemStack
    ) : Item.Mutable, Adapter<ItemStack>(baseItem) {

        override val type: ItemType
            get() = SpongeItemType(base.type)

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

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
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

        override var durability: Int
            get() = base.get(Keys.ITEM_DURABILITY).orElse(0)
            set(value) {
                base.offer(Keys.ITEM_DURABILITY, value)
            }

        override fun isSimilar(item: Item): Boolean {
            var other = (item as SpongeItem).baseItem
            if (other.quantity != base.quantity) {
                other = other.copy()
                other.quantity = base.quantity
            }
            return base.equalTo(other)
        }

        override fun mutableCopy(): Item.Mutable {
            return SpongeItem.Mutable(base.copy())
        }

        override fun immutableCopy(): Item {
            return SpongeItem(base.copy())
        }
    }
}
