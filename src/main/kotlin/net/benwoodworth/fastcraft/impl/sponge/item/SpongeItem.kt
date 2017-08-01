package net.benwoodworth.fastcraft.impl.sponge.item

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.impl.sponge.text.SpongeText
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.Sponge
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.ItemType
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

    class Builder : Item.Builder {

        private val builder = ItemStack.builder()

        private val postChanges = mutableListOf<(item: ItemStack) -> Unit>()

        override fun build(): Item {
            val result = builder.build()
            postChanges.forEach { it(result) }
            return SpongeItem(result)
        }

        override fun from(item: Item): Item.Builder {
            builder.fromItemStack(
                    (item.mutableCopy() as SpongeItem.Mutable).base
            )
            return this
        }

        override fun type(typeId: String): Item.Builder {
            val itemType = Sponge.getRegistry().getType(ItemType::class.java, typeId)

            builder.itemType(itemType.get())
            return this
        }

        override fun amount(amount: Int): Item.Builder {
            builder.quantity(amount)
            return this
        }

        override fun displayName(displayName: Text?): Item.Builder {
            postChanges += { item ->
                item.transform(Keys.DISPLAY_NAME) {
                    (displayName as SpongeText?)?.base
                }
            }
            return this
        }

        override fun lore(vararg lore: Text?): Item.Builder {
            postChanges += { item ->
                item.transform(Keys.ITEM_LORE) {
                    lore.map { (it as SpongeText?)?.base }
                }
            }
            return this
        }
    }
}
