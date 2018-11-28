package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextBuilder
import net.benwoodworth.fastcraft.util.invoke
import org.bukkit.inventory.ItemStack
import javax.inject.Provider

@Suppress("ClassName")
class Bukkit_11300R01_FcItem(
    private val itemStack: ItemStack,
    private val buildText: Provider<FcTextBuilder>
) : FcItem {

    override val type: Bukkit_11300R01_FcItemType
        get() = Bukkit_11300R01_FcItemType(itemStack.type)

    override val amount: Int
        get() = itemStack.amount

    override val displayName: FcText
        get() = itemStack.displayName
            ?.let { buildText { text = it } }
            ?: buildText { translate = type.name }

    override val lore: List<FcText>
        get() = itemStack.lore
            ?.map { buildText { text = it } }
            ?: emptyList()

    override val maxAmount: Int
        get() = itemStack.maxStackSize

    fun toItemStack(): ItemStack {
        return itemStack.clone()
    }
}
