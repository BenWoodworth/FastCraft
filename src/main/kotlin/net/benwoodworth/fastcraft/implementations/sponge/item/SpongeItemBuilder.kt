package net.benwoodworth.fastcraft.implementations.sponge.item

import net.benwoodworth.fastcraft.dependencies.item.Item
import net.benwoodworth.fastcraft.dependencies.item.ItemBuilder
import net.benwoodworth.fastcraft.dependencies.text.Text
import net.benwoodworth.fastcraft.implementations.sponge.text.SpongeText
import org.spongepowered.api.Sponge
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.ItemType
import org.spongepowered.api.item.inventory.ItemStack

class SpongeItemBuilder : ItemBuilder {

    private val builder = ItemStack.builder()

    private val postChanges = mutableListOf<(item: ItemStack) -> Unit>()

    override fun build(): Item {
        val result = builder.build()
        postChanges.forEach { it(result) }
        return SpongeItem(result)
    }

    override fun from(item: Item): ItemBuilder {
        builder.fromItemStack(
                (item.mutableCopy() as SpongeItem.Mutable).base
        )
        return this
    }

    override fun type(typeId: String): ItemBuilder {
        val itemType = Sponge.getRegistry().getType(ItemType::class.java, typeId)

        builder.itemType(itemType.get())
        return this
    }

    override fun amount(amount: Int): ItemBuilder {
        builder.quantity(amount)
        return this
    }

    override fun displayName(displayName: Text?): ItemBuilder {
        postChanges += {
            it.transform(Keys.DISPLAY_NAME) {
                (displayName as SpongeText?)?.base
            }
        }
        return this
    }

    override fun lore(vararg lore: Text?): ItemBuilder {
        postChanges += {
            it.transform(Keys.ITEM_LORE) {
                lore.map { (it as SpongeText?)?.base }
            }
        }
        return this
    }

    override fun durability(durability: Int) {
        postChanges += {
            it.offer(Keys.ITEM_DURABILITY, durability)
        }
    }
}