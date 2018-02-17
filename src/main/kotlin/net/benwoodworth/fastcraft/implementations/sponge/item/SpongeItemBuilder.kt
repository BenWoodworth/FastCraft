package net.benwoodworth.fastcraft.implementations.sponge.item

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.item.ItemType
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeText
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.inventory.ItemStack
import javax.inject.Inject

class SpongeItemBuilder @Inject constructor(
) : ItemBuilder {

    private val builder = ItemStack.builder()

    private val postChanges = mutableListOf<(item: ItemStack) -> Unit>()

    override fun build(): Item {
        val result = builder.build()
        postChanges.forEach { it(result) }
        return SpongeItem(result)
    }

    override fun from(item: Item) = also {
        builder.fromItemStack(
                (item.mutableCopy() as SpongeItem.Mutable).base
        )
    }

    override fun type(type: ItemType) = also {
        builder.itemType((type as SpongeItemType).base)
    }

    override fun amount(amount: Int) = also {
        builder.quantity(amount)
    }

    override fun displayName(displayName: Text?) = also {
        postChanges += {
            it.transform(Keys.DISPLAY_NAME) {
                (displayName as SpongeText?)?.base
            }
        }
    }

    override fun lore(lore: List<Text?>) = also {
        postChanges += {
            it.transform(Keys.ITEM_LORE) {
                lore.map { (it as SpongeText?)?.base }
            }
        }
    }

    override fun durability(durability: Int) = also {
        postChanges += {
            it.offer(Keys.ITEM_DURABILITY, durability)
        }
    }
}