package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.text.Bukkit_11300R01_FcTextText
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.text.FcText
import org.bukkit.inventory.ItemStack

@Suppress("ClassName")
class Bukkit_11300R01_FcItem(
    private val itemStack: ItemStack
) : FcItem {

    override val type: Bukkit_11300R01_FcItemType
        get() = Bukkit_11300R01_FcItemType(itemStack.type)

    override val amount: Int
        get() = itemStack.amount

    override val displayName: FcText?
        get() = itemStack.displayName
            ?.let { Bukkit_11300R01_FcTextText(it) }

    override val lore: List<FcText>?
        get() = itemStack.lore
            ?.map { Bukkit_11300R01_FcTextText(it) }

    override val maxStackSize: Int
        get() = itemStack.maxStackSize

    override val durability: Int
        get() = itemStack.durability.toInt()
}
