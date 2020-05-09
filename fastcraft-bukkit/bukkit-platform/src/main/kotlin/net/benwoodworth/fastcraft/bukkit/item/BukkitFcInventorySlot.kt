package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcInventorySlot
import net.benwoodworth.fastcraft.platform.item.FcItem
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*
import javax.inject.Inject

class BukkitFcInventorySlot(
    val inventory: Inventory,
    val slotIndex: Int,
    val itemFactory: FcItem.Factory,
) : FcInventorySlot {
    override var item: FcItem?
        get() = inventory.getItem(slotIndex).fromInventoryItem()
        set(value) {
            inventory.setItem(slotIndex, value.toInventoryItem())
        }

    private fun FcItem?.toInventoryItem(): ItemStack {
        return this
            ?.toItemStack()
            ?: ItemStack(Material.AIR, 0)
    }

    private fun ItemStack?.fromInventoryItem(): FcItem? {
        return this
            ?.takeUnless { it.type == Material.AIR }
            ?.let { itemFactory.createFcItem(it) }
    }

    override fun equals(other: Any?): Boolean {
        return other is FcInventorySlot &&
                inventory == other.inventory &&
                slotIndex == other.slotIndex
    }

    override fun hashCode(): Int {
        return Objects.hash(inventory, slotIndex)
    }

    class Factory @Inject constructor(
        val itemFactory: FcItem.Factory,
    ) {
        fun create(inventory: Inventory, slotIndex: Int): BukkitFcInventorySlot {
            return BukkitFcInventorySlot(
                inventory = inventory,
                slotIndex = slotIndex,
                itemFactory = itemFactory,
            )
        }
    }
}

val FcInventorySlot.inventory: Inventory
    get() = (this as BukkitFcInventorySlot).inventory

val FcInventorySlot.slotIndex: Int
    get() = (this as BukkitFcInventorySlot).slotIndex
