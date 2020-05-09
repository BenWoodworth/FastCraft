package net.benwoodworth.fastcraft.bukkit.item

import net.benwoodworth.fastcraft.platform.item.FcInventorySlot
import net.benwoodworth.fastcraft.platform.item.FcItemStack
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*
import javax.inject.Inject

class BukkitFcInventorySlot(
    val inventory: Inventory,
    val slotIndex: Int,
    val itemStackFactory: FcItemStack.Factory,
) : FcInventorySlot {
    override var itemStack: FcItemStack?
        get() = inventory.getItem(slotIndex).fromInventoryItem()
        set(value) {
            inventory.setItem(slotIndex, value.toInventoryItem())
        }

    private fun FcItemStack?.toInventoryItem(): ItemStack {
        return this
            ?.toBukkitItemStack()
            ?: ItemStack(Material.AIR, 0)
    }

    private fun ItemStack?.fromInventoryItem(): FcItemStack? {
        return this
            ?.takeUnless { it.type == Material.AIR }
            ?.let { itemStackFactory.create(it) }
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
        val itemStackFactory: FcItemStack.Factory,
    ) {
        fun create(inventory: Inventory, slotIndex: Int): BukkitFcInventorySlot {
            return BukkitFcInventorySlot(
                inventory = inventory,
                slotIndex = slotIndex,
                itemStackFactory = itemStackFactory,
            )
        }
    }
}

val FcInventorySlot.inventory: Inventory
    get() = (this as BukkitFcInventorySlot).inventory

val FcInventorySlot.slotIndex: Int
    get() = (this as BukkitFcInventorySlot).slotIndex
