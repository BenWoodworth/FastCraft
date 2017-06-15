package net.benwoodworth.fastcraft.sponge.dependencies.inventory

import net.benwoodworth.fastcraft.core.dependencies.inventory.FcItem
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.text.serializer.TextSerializers

/**
 * Created by ben on 6/5/17.
 */
class SpongeItem(
        override val base: ItemStack
) : FcItem<ItemStack> {

    override var amount: Int
        get() = base.quantity
        set(value) {
            base.quantity = value
        }

    override var displayName: String?
        get() {
            val displayName = base.get(Keys.DISPLAY_NAME)
            if (!displayName.isPresent) {
                return null
            }
            return TextSerializers
                    .LEGACY_FORMATTING_CODE
                    .serialize(displayName.get())
        }
        set(value) {
            base.offer(Keys.DISPLAY_NAME,
                    if (value == null) {
                        null
                    } else {
                        TextSerializers
                                .LEGACY_FORMATTING_CODE
                                .deserialize(value)
                    }
            )
        }

    override var lore: List<String?> // TODO Don't use legacy formatting.
        get() {
            return base
                    .get(Keys.ITEM_LORE)
                    .orElse(emptyList())
                    .map {
                        TextSerializers
                                .LEGACY_FORMATTING_CODE
                                .serialize(it)
                    }
        }
        set(value) {
            base.offer(
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
        get() = base.maxStackQuantity

    override val hasWildCardData: Boolean
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun clone(): FcItem<ItemStack> {
        return SpongeItem(base.copy())
    }

    override fun addEnchantment(enchantmentId: String, level: Int, ignoreLevelRestriction: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isSimilar(item: FcItem<ItemStack>): Boolean {
        var other = item.base
        if (other.quantity != base.quantity) {
            other = other.copy()
            other.quantity = base.quantity
        }
        return base.equalTo(other)
    }

    override fun matchesIngredient(ingredient: FcItem<ItemStack>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun equals(other: Any?) = false

    fun equals(other: FcItem<ItemStack>) = base == other.base

    override fun hashCode() = base.hashCode()

}
