package net.benwoodworth.fastcraft.sponge.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.inventory.Item
import net.benwoodworth.fastcraft.core.dependencies.text.Text
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import org.spongepowered.api.item.inventory.ItemStack

/**
 * Adapts Sponge items.
 */
@Suppress("DEPRECATION") // TODO Don't use legacy formatting.
class SpongeItemAdapter(
        baseItem: ItemStack
) : Item, Adapter<ItemStack>(baseItem) {

    override var amount: Int
        get() = base.quantity
        set(value) {
            base.quantity = value
        }

    override var displayName: Text?
        get() = TODO()
        set(value) = TODO()

    override var lore: List<Text?>
        get() = TODO()
        set(value) = TODO()

    override val maxStackSize: Int
        get() = base.maxStackQuantity

    override val hasWildCardData: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun clone(): Item {
        return SpongeItemAdapter(base.copy())
    }

    override fun addEnchantment(enchantmentId: String, level: Int, ignoreLevelRestriction: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

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

    override fun matchesIngredient(ingredient: Item): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun equals(other: Any?): Boolean {
        if (other is SpongeItemAdapter) {
            return base == other.base
        }
        return false
    }

    override fun hashCode() = base.hashCode()

}
