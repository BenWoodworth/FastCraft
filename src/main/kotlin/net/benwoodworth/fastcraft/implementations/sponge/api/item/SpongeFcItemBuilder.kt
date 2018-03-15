package net.benwoodworth.fastcraft.implementations.sponge.api.item

import net.benwoodworth.fastcraft.dependencies.api.item.FcItem
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemBuilder
import net.benwoodworth.fastcraft.dependencies.api.item.FcItemType
import net.benwoodworth.fastcraft.dependencies.api.text.FcText
import net.benwoodworth.fastcraft.implementations.sponge.api.text.SpongeFcText
import org.spongepowered.api.data.key.Keys
import org.spongepowered.api.item.inventory.ItemStack
import javax.inject.Inject

class SpongeFcItemBuilder @Inject constructor(
) : FcItemBuilder {

    private val builder = ItemStack.builder()

    private val postChanges = mutableListOf<(item: ItemStack) -> Unit>()

    override fun build(): FcItem {
        val result = builder.build()
        postChanges.forEach { it(result) }
        return SpongeFcItem(result)
    }

    override fun from(item: FcItem) = also {
        builder.fromItemStack(
                (item.mutableCopy() as SpongeFcItem.Mutable).base
        )
    }

    override fun type(type: FcItemType) = also {
        builder.itemType((type as SpongeFcItemType).base)
    }

    override fun amount(amount: Int) = also {
        builder.quantity(amount)
    }

    override fun displayName(displayName: FcText?) = also {
        postChanges += {
            it.transform(Keys.DISPLAY_NAME) {
                (displayName as SpongeFcText?)?.base
            }
        }
    }

    override fun lore(lore: List<FcText?>) = also {
        postChanges += {
            it.transform(Keys.ITEM_LORE) {
                lore.map { (it as SpongeFcText?)?.base }
            }
        }
    }

    override fun durability(durability: Int) = also {
        postChanges += {
            it.offer(Keys.ITEM_DURABILITY, durability)
        }
    }
}