package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItem.Companion.itemStack
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItem.Companion.toItemStack
import net.benwoodworth.fastcraft.bukkit.item.BukkitFcItemTypes.Companion.fromMaterialData
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemType
import net.benwoodworth.fastcraft.platform.item.FcItemTypes
import net.benwoodworth.fastcraft.platform.text.FcText
import net.benwoodworth.fastcraft.platform.text.FcTextFactory
import org.bukkit.inventory.ItemStack
import javax.inject.Inject

open class BukkitFcItem_1_7(
    override val itemStack: ItemStack,
    protected val itemTypes: FcItemTypes,
    protected val textFactory: FcTextFactory,
) : BukkitFcItem {
    override val type: FcItemType
        get() = itemTypes.fromMaterialData(itemStack.data)

    override val amount: Int
        get() = itemStack.amount

    override val name: FcText
        get() = itemStack
            .takeIf { it.hasItemMeta() }
            ?.itemMeta
            ?.takeIf { it.hasDisplayName() }
            ?.displayName
            ?.let { textFactory.createFcText(it) }
            ?: type.blockName

    override val lore: List<FcText>
        get() = itemStack
            .takeIf { it.hasItemMeta() }
            ?.itemMeta
            ?.takeIf { it.hasLore() }
            ?.lore
            ?.map { textFactory.createFcText(it ?: "") }
            ?: emptyList()

    override val hasMetadata: Boolean
        get() = itemStack.hasItemMeta()

    override fun toItemStack(): ItemStack {
        return itemStack.clone()
    }

    override fun equals(other: Any?): Boolean {
        return other is FcItem && itemStack == other.itemStack
    }

    override fun hashCode(): Int {
        return itemStack.hashCode()
    }

    open class Factory @Inject constructor(
        protected val itemTypes: FcItemTypes,
        protected val textFactory: FcTextFactory,
    ) : BukkitFcItem.Factory {
        override fun copyItem(item: FcItem, amount: Int): FcItem {
            if (amount == item.amount) {
                return item
            }

            val itemStack = item.toItemStack()
            itemStack.amount = amount

            return createFcItem(itemStack)
        }

        override fun createFcItem(itemStack: ItemStack): FcItem {
            return BukkitFcItem_1_7(
                itemStack = itemStack,
                itemTypes = itemTypes,
                textFactory = textFactory
            )
        }
    }
}
