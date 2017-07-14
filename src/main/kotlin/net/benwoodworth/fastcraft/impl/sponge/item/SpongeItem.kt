package net.benwoodworth.fastcraft.impl.sponge.item

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.util.Adapter
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.text.Text as Sponge_Text

/**
 * Adapts Sponge items.
 */
class SpongeItem(
        baseItem: ItemStack
) : Item, Adapter<ItemStack>(baseItem) {

    override var amount: Int
        get() = base.quantity
        set(value) {
            base.quantity = value
        }

    override val name: Text
        get() = net.benwoodworth.fastcraft.impl.sponge.text.SpongeText(Sponge_Text.of(base))

    override var displayName: Text?
        get() {
            val displayName = base.get(Keys.DISPLAY_NAME).orElse(null)
            return net.benwoodworth.fastcraft.impl.sponge.text.SpongeText(displayName)
        }
        set(value) {
            value as net.benwoodworth.fastcraft.impl.sponge.text.SpongeText
            base.offer(Keys.DISPLAY_NAME, value.base)
        }

    override var lore: List<Text?>?
        get() {
            val lore = base.get(Keys.ITEM_LORE).orElse(null)
            return lore?.map {
                it?.run { net.benwoodworth.fastcraft.impl.sponge.text.SpongeText(it) }
            }
        }
        set(value) {
            base.offer(Keys.ITEM_LORE, value?.map {
                it?.run { (this as net.benwoodworth.fastcraft.impl.sponge.text.SpongeText).base }
            })
        }

    override val maxStackSize: Int
        get() = base.maxStackQuantity

    override fun isSimilar(item: Item): Boolean {
        if (item !is SpongeItem) {
            return false
        }

        var other = item.base
        if (other.quantity != base.quantity) {
            other = other.copy()
            other.quantity = base.quantity
        }
        return base.equalTo(other)
    }

    override fun copy() = SpongeItem(base.copy())
}
