package net.benwoodworth.fastcraft.bukkit.item

import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import net.benwoodworth.fastcraft.platform.item.FcInventorySlot
import net.benwoodworth.fastcraft.platform.item.FcItem
import net.benwoodworth.fastcraft.platform.item.FcItemFactory
import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*

@AutoFactory
class BukkitFcInventorySlot(
    val inventory: Inventory,
    val slotIndex: Int,
    @Provided val itemFactory: FcItemFactory
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
}

val FcInventorySlot.inventory: Inventory
    get() = (this as BukkitFcInventorySlot).inventory

val FcInventorySlot.slotIndex: Int
    get() = (this as BukkitFcInventorySlot).slotIndex
