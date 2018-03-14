package net.benwoodworth.fastcraft.implementations.sponge.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemType
import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.implementations.sponge.api.text.SpongeFcText
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.text.Text

/**
 * Sponge implementation of [FcItem].
 *
 * @property base The item being adapted. Should not be modified.
 */
class SpongeFcItem(
        private val base: ItemStack
) : FcItem by SpongeFcItem.Mutable(base) {

    val snapshot by lazy { base.createSnapshot() }

    class Mutable(
            override val base: ItemStack
    ) : FcItem.Mutable, Adapter<ItemStack>() {

        override val type: FcItemType
            get() = SpongeFcItemType(base.type)

        override var amount: Int
            get() = base.quantity
            set(value) {
                base.quantity = value
            }

        override val name: FcText
            get() = SpongeFcText(Text.of(base))

        override var displayName: FcText?
            get() {
                val displayName = base.get(Keys.DISPLAY_NAME).orElse(null)
                return SpongeFcText(displayName)
            }
            set(value) {
                value as SpongeFcText
                base.offer(Keys.DISPLAY_NAME, value.base)
            }

        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        override var lore: List<FcText?>?
            get() {
                val lore = base.get(Keys.ITEM_LORE).orElse(null)
                return lore?.map {
                    it?.run { SpongeFcText(it) }
                }
            }
            set(value) {
                base.offer(Keys.ITEM_LORE, value?.map {
                    it?.let { (it as SpongeFcText).base }
                })
            }

        override val maxStackSize: Int
            get() = base.maxStackQuantity

        override var durability: Int
            get() = base.get(Keys.ITEM_DURABILITY).orElse(0)
            set(value) {
                base.offer(Keys.ITEM_DURABILITY, value)
            }

        override fun isSimilar(item: FcItem): Boolean {
            var other = (item as SpongeFcItem).base
            if (other.quantity != base.quantity) {
                other = other.copy()
                other.quantity = base.quantity
            }
            return base.equalTo(other)
        }

        override fun mutableCopy(): FcItem.Mutable {
            return SpongeFcItem.Mutable(base.copy())
        }

        override fun immutableCopy(): FcItem {
            return SpongeFcItem(base.copy())
        }
    }
}
