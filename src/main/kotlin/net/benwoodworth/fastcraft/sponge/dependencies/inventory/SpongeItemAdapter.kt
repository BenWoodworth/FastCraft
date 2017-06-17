package net.benwoodworth.fastcraft.sponge.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.inventory.Item
import net.benwoodworth.fastcraft.core.dependencies.util.Adapter
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.text.serializer.TextSerializers

/**
 * Adapts Sponge items.
 */
@Suppress("DEPRECATION") // TODO Don't use legacy formatting.
class SpongeItemAdapter(
        private val baseItem: ItemStack
) : Item, Adapter<ItemStack>(baseItem) {

    override var amount: Int
        get() = baseItem.quantity
        set(value) {
            baseItem.quantity = value
        }

    override var displayName: String?
        get() {
            val displayName = baseItem.get(Keys.DISPLAY_NAME)
            if (!displayName.isPresent) {
                return null
            }
            return TextSerializers
                    .LEGACY_FORMATTING_CODE
                    .serialize(displayName.get())
        }
        set(value) {
            baseItem.offer(Keys.DISPLAY_NAME,
                    if (value == null) {
                        null
                    } else {
                        TextSerializers
                                .LEGACY_FORMATTING_CODE
                                .deserialize(value)
                    }
            )
        }

    override var lore: List<String?>
        get() {
            return baseItem
                    .get(Keys.ITEM_LORE)
                    .orElse(emptyList())
                    .map {
                        TextSerializers
                                .LEGACY_FORMATTING_CODE
                                .serialize(it)
                    }
        }
        set(value) {
            baseItem.offer(
                    Keys.ITEM_LORE,
                    value.map {
                        if (it == null) {
                            null
                        } else {
                            TextSerializers
                                    .LEGACY_FORMATTING_CODE
                                    .deserialize(it)
                        }
                    }
            )
        }

    override val maxStackSize: Int
        get() = baseItem.maxStackQuantity

    override val hasWildCardData: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun clone(): Item {
        return SpongeItemAdapter(baseItem.copy())
    }

    override fun addEnchantment(enchantmentId: String, level: Int, ignoreLevelRestriction: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isSimilar(item: Item): Boolean {
        if (item !is SpongeItemAdapter) {
            return false
        }

        var other = item.baseItem
        if (other.quantity != baseItem.quantity) {
            other = other.copy()
            other.quantity = baseItem.quantity
        }
        return baseItem.equalTo(other)
    }

    override fun matchesIngredient(ingredient: Item): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun equals(other: Any?) = false

    fun equals(other: SpongeItemAdapter) = baseItem == other.baseItem

    override fun hashCode() = baseItem.hashCode()

}
