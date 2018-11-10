package net.benwoodworth.fastcraft.bukkit.item

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import org.bukkit.inventory.ItemStack
import javax.inject.Provider

@AutoFactory
@Suppress("ClassName")
class Bukkit_11300R01_FcItem(
    private val itemStack: ItemStack,

    @Provided private val textBuilder: Provider<FcTextBuilder>
) : FcItem {

    override val type: Bukkit_11300R01_FcItemType
        get() = Bukkit_11300R01_FcItemType(itemStack.type, textBuilder)

    override val amount: Int
        get() = itemStack.amount

    override val displayName: FcText
        get() = when (val displayName = itemStack.displayName) {
            null -> type.name

            else -> textBuilder.get()
                .text(displayName)
                .build()
        }

    override val lore: List<FcText>
        get() = itemStack.lore
            ?.map {
                textBuilder.get()
                    .text(it)
                    .build()
            }
            ?: emptyList()

    override val maxAmount: Int
        get() = itemStack.maxStackSize

    fun getItemStackCopy(): ItemStack {
        return itemStack.clone()
    }
}
