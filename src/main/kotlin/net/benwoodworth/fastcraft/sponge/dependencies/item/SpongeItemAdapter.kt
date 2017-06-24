package net.benwoodworth.fastcraft.sponge.dependencies.item

import net.benwoodworth.fastcraft.core.dependencies.item.Item
import net.benwoodworth.fastcraft.core.dependencies.text.Text
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import net.benwoodworth.fastcraft.sponge.dependencies.text.SpongeTextAdapter
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.text.Text as SpongeText

/**
 * Adapts Sponge items.
 */
class SpongeItemAdapter(
        baseItem: ItemStack
) : Item, Adapter<ItemStack>(baseItem) {

    override var amount: Int
        get() = base.quantity
        set(value) {
            base.quantity = value
        }

    override val name: Text
        get() = SpongeTextAdapter(SpongeText.of(base))

    override var displayName: Text?
        get() {
            val displayName = base.get(Keys.DISPLAY_NAME).orElse(null)
            return SpongeTextAdapter(displayName)
        }
        set(value) {
            base.offer(Keys.DISPLAY_NAME, (value as SpongeTextAdapter).base)
        }

    override var lore: List<Text?>?
        get() {
            val lore = base.get(Keys.ITEM_LORE).orElse(null)
            return lore?.map {
                it?.run { SpongeTextAdapter(it) }
            }
        }
        set(value) {
            base.offer(Keys.ITEM_LORE, value?.map {
                it?.run { (this as SpongeTextAdapter).base }
            })
        }

    override val maxStackSize: Int
        get() = base.maxStackQuantity

    override fun isSimilar(item: Item): Boolean {
        if (item !is SpongeItemAdapter) {
            return false
        }

        var other = item.base
        if (other.quantity != base.quantity) {
            other = other.copy()
            other.quantity = base.quantity
        }
        return base.equalTo(other)
    }
}
